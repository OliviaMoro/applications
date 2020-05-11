class Case{

	constructor(position){
		this.position = position;
		this.objet = null;
		this.accessible = true;
		this.length = 80;
		this.height = 80;
		this.nom = "";
	}

	getNom(){
		return this.name;
	}

	getPosition(){
		return this.position;
	}

	setPosition(position){
		this.position = position;
	}

	getLength(){
		return this.length;
	}

	getHeight(){
		return this.height;
	}

	getObjet(){
		return this.objet;
	}

	setObjet(objet){
		this.objet = objet;
	}

	isAccessible(){
		return this.accessible;
	}

	setAccessible(accessible){
		this.accessible = accessible;
	}

}