class Arme{

	constructor(nom = "arme initiale",degat = 10){
		this.nom = nom;
		this.degat = degat;
	}

	getNom(){
		return this.nom;
	}

	getDegat(){
		return this.degat;
	}

	draw(ctx,x,y){
		ctx.fillStyle = "Grey";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Defaut", x+10, y+45);
	    return ctx;
	}
}