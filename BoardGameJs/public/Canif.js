class Canif extends Arme{

	constructor(){
		super("canif",40);
	}


	draw(ctx,x,y){
	    ctx.fillStyle ="LightBlue"
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Canif", x+10, y+45);
	    return ctx;
	}
}