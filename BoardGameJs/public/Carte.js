class Carte{
	// 0 = case vide
	// 1 = mur
	// 2 = case vide avec un canif
	// 3 = case vide avec un pistolet
	// 4 = case vide avec un fusil
	// 5 = case vide avec une barre
	// 6 = case vide avec une arme

	constructor(){
		this.nRow = 10;
		this.nCol = 10;
		this.carteRep = this.setCarteRep();
		this.carte = this.initCarte();
	}


	setCarteRep(){
		var carteRep = [[0, 0, 0, 0, 1, 0, 1, 0, 0, 0],
						[0, 0, 0, 0, 1, 0, 0, 1, 0, 0],
						[0, 0, 0, 0, 0, 1, 0, 0, 1, 0],
						[0, 0, 0, 0, 0, 0, 1, 0, 0, 0],
						[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
						[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
						[0, 3, 0, 0, 0, 0, 0, 0, 0, 2],
						[0, 0, 0, 1, 0, 1, 0, 1, 0, 0],
						[0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
						[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]];

		return carteRep;
	}

    //Utiliser uniquement lors de l'initialisation
	initCarte(carteRep){
        var position;
        var carte = [];

		for (var i = 0; i < this.getNRow(); i++) {
			var ligne = []
			for (var j = 0; j < this.getNCol(); j++) {
				var currentCase = this.carteRep[i][j];
				var newCase;
				position = new Position(i,j);
				switch(currentCase){
					case 0:
						newCase = new Vide(position);
						break;
					case 1:
						newCase = new Mur(position);
						break;
					case 2:
						newCase = new Vide(position);
						newCase.setObjet(new Canif());
						break;
					case 3:
						newCase = new Vide(position);
						newCase.setObjet(new Pistolet());
						break;
					case 4:
						newCase = new Vide(position);
						newCase.setObjet(new Fusil());
						break;	
					case 5:
						newCase = new Vide(position);
						newCase.setObjet(new Barre());
						break; 
				}
				ligne.push(newCase);
			}
			carte.push(ligne);
		} 
		return carte;
	}

	getCarte(){
		return this.carte;
	}

	setCarte(carte){
		this.carte;
	}

	getCaseRep(row,col){
		return this.carteRep[row][col];
	}

	setCaseRep(row,col,index){
		//6 : indice représentant un joueur
		this.carteRep[row][col] = index;
	}

	getCase(row,col){
		return this.carte[row][col];
	}

	setCase(row,col,objet){
		var pos = this.carte[row][col];
		pos.setObjet(objet);
		return carte;
	}

	getNRow(){
		return this.nRow;
	}

	getNCol(){
		return this.nCol;
	}



}
