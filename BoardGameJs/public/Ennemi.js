class Ennemi extends Joueur{

	constructor(){
		super("ennemi");
	}

	draw(ctx,x,y){
		ctx.fillStyle = "Red";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Ennemi", x+10, y+45);
	    return ctx;
	}
}