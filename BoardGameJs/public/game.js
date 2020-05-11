
var socket = io.connect('http://localhost:3000');

window.addEventListener('load',function(){
    console.log('connexion');

    socket.on('success',(msg)=>alert(msg));
    socket.on('err',function(msg){
        alert(msg);
    });    
});

//Variable du document
var nom = document.getElementById('nom'),
    pv = document.getElementById('pv'),
    arme = document.getElementById('arme'),
    dommage = document.getElementById('dommage'),
    turn = document.getElementById('tour'),
    action = document.getElementById('action'),
    defence = document.getElementById('defence'),
    attaque = document.getElementById('attaque'),
    canvas = document.getElementById('game'),
    ctx = canvas.getContext("2d");


//Variables Partie
var carte = new Carte(),
    caseVide = new Vide(),
    pseudo, 
    player,
    isStart = false,
    isTurn = false,
    isDuel = false,
    turnTxt = 0,
    actionTxt = "déplacement",
    challenger = new Ennemi(),
    oldCase = null,
    objet = null;


function playerData(player){ //Met à jour les information sur le personnage du client
    nom.textContent = player.getNom();
    pv.textContent = player.getPV();
    arme.textContent = player.getArme().getNom();
    dommage.textContent = player.getArme().getDegat();
    turn.textContent = turnTxt;
    action.textContent = actionTxt;
}


function resetData(){
    nom.textContent = "";
    pv.textContent = "";
    arme.textContent = "";
    dommage.textContent = "";
    turn.textContent = "";
    action.textContent = "";
}

//Initialisation :
var game = new DrawGame(ctx,carte);
ctx = game.drawGame(ctx,carte,player);
//Mise a jour :
socket.on('start', (indexPlr) => {
    pseudo = prompt(`Veuillez indiquer votre pseudo :`);
    player = new Joueur(pseudo);

    //Mise à jour du client :
    var pos = player.initialPosition(carte);
    console.log(`player init : ${player.getNom()},(${pos.getRow()},${pos.getCol()})`);
    carte.setCase(pos.getRow(),pos.getCol(),player);   
    playerData(player);
    ctx = game.drawGame(ctx,carte,player);

    //On envoie au serveur :
    socket.emit('update',{row: pos.getRow(), col: pos.getCol(), pseudo: player.getNom()});
    (indexPlr==0)?socket.emit('hasInit',"Initialisation"):console.log("has init");
});


//On reçoit des données du joueur enemi : challenger
socket.on('update', (data) => {
    var oldPos, pos = new Position(data.row,data.col);
    
    if(isStart && !isDuel){         // Déplacements : hors duel        
        oldPos = challenger.getPosition();  
        carte.setCase(oldPos.getRow(),oldPos.getCol(),objet); 
    } else if(isDuel && data.dom) { // Duel : on récupère les dégats subits
        var degat = parseInt((actionTxt=="Défence")?data.dom/2.:data.dom);
        this.player.setPV(player.getPV()-degat);
        if(player.getPV() <= 0){    // Défaite
            isStart = false;
        	socket.emit('defeat',`Joueur ${player.getNom()} a perdu.`);   
        }
        playerData(player);
        isDuel = false;
        //socket.emit('hasLost',isStart);
    } else {
        console.log(`position ennemi : (${data.row},${data.col})`);
        isStart = true;
    }

    if (data.arme){ // Pour les changements d'arme       
        const foo = data.arme
        objet = eval(`new ${foo}()`);
    }
    objet = (data.arme)?objet:null;
    carte = challenger.setPosition(pos,carte);
    ctx = game.drawGame(ctx,carte,player);
});


socket.on('turn',(message) => {
    alert(`${message} : déplacement horizontaux ou verticaux de 3 cases au plus`);
    isTurn = true;
    turnTxt++;
    playerData(player);
});


socket.on('duel',(message) => {
    isDuel = true;
    turnTxt++;
    (player.getPV()>0)?alert(`${message}`):console.log("FIN");
    playerData(player);
});


socket.on('end',(message) => {
	isDuel = false;
    isStart = false;
	alert(message);
	carte = new Carte();
	ctx = game.drawGame(ctx,carte,player);
    resetData()
});


attaque.addEventListener('click',(e) => {
    actionTxt = "Attaque";
    var data = {
        row : player.getPosition().getRow(),
        col : player.getPosition().getCol(),
        pseudo : player.getNom(),
        dom : player.getArme().getDegat()
    } 
    socket.emit('update',data);
    playerData(player);
});


defence.addEventListener('click',(e) => {
    actionTxt = "Défence";
    var data = {
        row : player.getPosition().getRow(),
        col : player.getPosition().getCol(),
        pseudo : player.getNom(),
        dom : 0
    } 
    socket.emit('update',data);
    playerData(player);
});


canvas.addEventListener('click', function(e){ // Gère les déplacements et les changements d'arme
    var accepted = false,
        change = false,
        oldArme = player.getArme(),
        x = e.pageX - canvas.offsetLeft,
        y = e.pageY - canvas.offsetTop,
        row = Math.trunc(y/80),
        col = Math.trunc(x/80);

    if(isTurn){
        [carte,accepted,change] = player.moveTo(new Position(row,col),carte,oldCase);
        
        if (accepted){
             ctx = game.drawGame(ctx,carte,player);
        }
        else {
            row = player.getPosition().getRow();
            col = player.getPosition().getCol();
            alert("Déplacement non autorisé.");
        }
        //On envoie au serveur 
        data = (change)?{row:row,col:col,pseudo:pseudo,arme:oldArme.constructor.name.toString()}:{row:row,col:col,pseudo:pseudo};
        oldCase = (change)?oldArme:null;
        socket.emit('update',data); 
        playerData(player);
        isTurn = false;
    }
});




