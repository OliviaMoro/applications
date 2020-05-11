class Joueur{
	constructor(nom){
		this.nom = nom;
		this.pv = 100;
		this.arme = new Arme();
		this.position = new Position(10,10);
	}

	getNom(){
		return this.nom;
	}

	setNom(nom){
		this.nom = nom;
	}

	getPV(){
		return this.pv;
	}

	setPV(pv){
		this.pv = pv;
	}

	getPosition(){
		return this.position;
	}

	setPosition(position,carte){
		this.position = position;
		carte.setCase(position.getRow(),position.getCol(),this);
		return carte;
	}

	getArme(){
		return this.arme;
	}

	setArme(arme){
		this.arme = arme;
	}

	changeArme(position,carte){
		//On se contente de récupérer l'arme de la case
		var oldArme = this.getArme();
		console.log(`oldArme : ${oldArme.getNom()}`);
		var arme = carte.getCase(position.getRow(),position.getCol()).getObjet();
		this.setArme(arme);

		return carte;
	}

	moveTo(position,carte,oldCase=null){
		var oldPosition = this.position,
		    oldRow = oldPosition.getRow(),
			oldCol = oldPosition.getCol(),
			newRow = position.getRow(),
			newCol = position.getCol(),
			accepted = false,
			change = false,
			accessible = carte.getCase(newRow,newCol).isAccessible();
			
		if(oldRow != newRow && oldCol != newCol){
			console.log(`Seul les déplacements horizontaux ou verticaux sont autorisés `);
		} else if (oldRow == newRow || oldCol == newCol){
			var delta = (oldRow-newRow != 0)?(oldRow-newRow):(oldCol-newCol);
			if(delta<=3 && accessible){
				if(carte.getCase(newRow,newCol).getObjet() instanceof Arme){
					console.log(`Il y a une arme : ${carte.getCase(newRow,newCol).getObjet().getNom()}`);
					carte = this.changeArme(position,carte);
					change = true;
				}
				carte.setCase(oldPosition.getRow(),oldPosition.getCol(),oldCase);	
				this.setPosition(position,carte);
				accepted = true;
			} else {
				console.log(`Déplacement non autorisé.`)
			}
		}
		return [carte,accepted,change];
	}

	initialPosition(carte){
		var isOk = false;
		var position = null;

		while(!isOk){
			var i = Math.floor((Math.random() * 9) + 1);
			var j = Math.floor((Math.random() * 9) + 1);
			var pos = carte.getCarte()[i][j];
			//La case ne doit rien contenir ni être un mur
			if(pos instanceof Vide && pos.getObjet()==null){
				isOk = true;
				position = new Position(i,j);
				carte.setCase(i,j,this);
				this.setPosition(position,carte);
			}
		}
		return position;
	}

	draw(ctx,x,y){
		ctx.fillStyle = "Gold";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Joueur", x+10, y+45);
	    return ctx;
	}

	/*draw(ctx,x,y){
	    ctx.strokeStyle = "Gold";
	    ctx.fillStyle = "Gold";
	    ctx.beginPath(); // Le cercle extérieur
	    ctx.arc(x+40, y+40, 35, 0, Math.PI * 2); 
	    ctx.stroke();

	    ctx.beginPath(); // La bouche, un arc de cercle
	    ctx.arc(x+40,y+40, 30, 0, Math.PI); // Ici aussi
	    ctx.fill();

	    ctx.beginPath(); // L'oeil gauche
	    ctx.arc(x+25, y+35, 15, (Math.PI / 180) * 220, (Math.PI / 180) * 320);
	    ctx.stroke();

	    ctx.beginPath(); // L'oeil droit
	    ctx.arc(x+55,y+35, 15, (Math.PI / 180) * 220, (Math.PI / 180) * 320);
	    ctx.stroke();

	    return ctx;
	}*/
}