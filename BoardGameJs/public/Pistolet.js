class Pistolet extends Arme{

	constructor(){
		super("pistolet",50);
	}


	draw(ctx,x,y){
	    ctx.fillStyle = "Brown";
	    ctx.fillRect(x+5, y+5, caseVide.getLength()-10, caseVide.getHeight()-10);
	    ctx.font = "20px Calibri,Geneva,Arial";
	    ctx.fillStyle = "white";
	    ctx.fillText("Pistolet", x+10, y+45);
	    return ctx;
	}
}