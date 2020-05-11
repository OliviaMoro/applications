class Barre extends Arme{

	constructor(){
		super("barre",30);
	}


	draw(ctx,x,y){
	    ctx.fillStyle = "Grey";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Barre", x+10, y+45);
	    return ctx;
	}
}