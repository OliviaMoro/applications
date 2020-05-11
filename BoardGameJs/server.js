function Joueur(id, nom, row, col) {
  this.id = id;
  this.nom = nom;
  this.row = row;
  this.col = col;
  this.dom = 0;
}

var express = require('express'),
	app = express(),
    server = app.listen(3000),
    socket = require('socket.io'),
    io = socket(server);

app.use(express.static('public'));


//Liste de joueurs :
var players = [];
var index = 0;
var playing = false;
var isDuel = false;
var winner;

//On reçoit une nouvelle connexion :
io.sockets.on('connection', (socket) => {
	console.log(`new connection ${socket.id}`);
	//Le joueur n'est pas connecté pour jouer à coup sûr :
	var isPlayer = false;
	index++;

	if (index <= 2) {    // On teste si le nombre de connexion n'exède pas 2
		socket.emit('success','Vous êtes maintenenant connecté au server.');
		if (index == 2){ // var message = 'Veuillez indiquer votre pseudo';
			socket.emit('start',0);
			socket.on('hasInit', (msg) => {
				socket.broadcast.emit('start',1);
			});
		}
	} else {
		socket.emit('err','Capacité maximale atteinte : une autre fois !');	
	}

	//Reçoit des mises à jour uniquement pour les joueurs sélectionnés
	socket.on('update', (data) => {
		isPlayer = true;
		var row = data.row,
			col = data.col,
			pseudo = data.pseudo;
		console.log(`${pseudo} envoie : (${row},${col})`);

		data = (data.arme)?{row:row,col:col,pseudo:pseudo,arme:data.arme}:data;	
		socket.broadcast.emit('update',data);

		if(!playing){
			players.push(new Joueur(socket.id, pseudo, row, col));
			if(players.length == 2){
				playing = true;
				//Après initialisation des deux joueur : c'est le tour du premier joueur ajouté
				socket.broadcast.emit('turn','A votre tour');
			}
		} else if(isDuel && playing) {
			console.log(`${data.pseudo} - dommages : ${data.dom}`);
			socket.broadcast.emit('update',{row:row,col:col,pseudo:pseudo,dom:data.dom});
			socket.broadcast.emit('duel','Défence ou attaque?');
			
		} else if(playing && !isDuel){
			//On met à jour la position du joueur :
			for(var i=0; i<2; i++){
				if(players[i].id == socket.id){
					console.log(`On met à jour ${players[i].nom} : (${row},${col})`);
					players[i].row = row;
					players[i].col = col;
				}
			}
			isDuel = testPosition(players);
			//On envoi un message pour indiqué au joueur suivant son tour
			(isDuel)?socket.broadcast.emit('duel','Défence ou attaque?'):socket.broadcast.emit('turn','A votre tour');
		}
	});

	socket.on('defeat', (msg) => {
		console.log(msg);
		playing = false;
		isDuel = false;
		for (var i = 0; i<players.length; i++){
			if(players[i].id != socket.id){
				winner = players[i].nom;
				console.log(`Le gagnant est ${winner}.`);
			}			
		}
		socket.emit('end',`Le gagnant est ${winner}.`);
		socket.broadcast.emit('end',`Le gagnant est ${winner}.`);

		// On vide la liste pour pouvoir rejouer une partie
		players.splice(0,players.length);
		index = 0;
	});

	//En cas de déconnexion :
	socket.on('disconnect', function() {
	    console.log(`Client ${socket.id} has disconnected`);
	    //Deconnexion pendant la partie : victoire par forfait pour le joueur restant 
	    if(playing && isPlayer){ 
	    	socket.broadcast.emit('end',"Victoire par forfait");
	    	players.splice(0,players.length);
	    	index = 0;
	    	playing = false;
	    	isDuel = false;
	    } 
	    //Deconnexion avant la partie : on remet à jours la liste
	    else if (!playing){     
	      	if(isPlayer){
		      	players.splice(index-1,index);
		      	index--;        		
		    }
	    }
    });

});

function testPosition(players){
    var P1 = players[0];
    var P2 = players[1];

    var deltaRow = Math.abs(P1.row-P2.row); 
    var deltaCol = Math.abs(P1.col-P2.col);

    console.log(`deltaRow : ${deltaRow}, deltaCol : ${deltaCol}`);
    console.log(`P1 : (${P1.row},${P1.col}), P2 : (${P2.row},${P2.col})`);

    isAligned = (deltaRow == 0) || (deltaCol == 0);
    isNear = (deltaRow == 1) || (deltaCol == 1);

    if(isAligned && isNear){
    	isDuel = true;
    }
    return isDuel;
}

