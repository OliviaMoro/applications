class Fusil extends Arme{

	constructor(){
		super("fusil",60);
	}


	draw(ctx,x,y){
	    ctx.fillStyle = "Indigo";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Fusil", x+10, y+45);
	    return ctx;
	}
}