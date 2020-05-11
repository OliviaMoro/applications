class DrawGame{
	caseVide = new Vide();

	constructor(ctx,carte){
		this.ctx = ctx;
		this.carte = carte;
	}


	drawBoard(ctx){
	    ctx.strokeStyle = "Black";
	    //ctx.beginPath();
	    for(var j=0; j<=carte.getNCol(); j++){
	        var y = j*caseVide.getLength();
	        ctx.moveTo(y,0);
	        ctx.lineTo(y,800);
	    }

	    for(var i=0; i<=carte.getNRow(); i++){
	        var x = i*caseVide.getHeight();
	        ctx.moveTo(0,x);
	        ctx.lineTo(800,x);
	    }
	    //ctx.closePath();
	    ctx.stroke();
	    return ctx;
	}


	drawGame(ctx,carte,player){ 
	    ctx.clearRect(0,0,canvas.width,canvas.height);
	    for(var i=0; i<carte.getNRow(); i++){
	        for(var j=0; j<carte.getNCol();j++){
	            var currentCase = carte.getCarte()[i][j];
	            var y = i*currentCase.getLength();
	            var x = j*currentCase.getHeight();
	            var nom;

	            if(currentCase.getObjet()==null){
	                nom = currentCase.getNom();
	                switch(nom){
	                    case "vide":
	                        ctx.fillStyle = "White";
	                        break;
	                    case "mur":
	                        ctx.fillStyle = "#ccffcc";
	                        break;
	                }
	                ctx.fillRect(x, y, currentCase.getLength(), currentCase.getHeight());
	            } else{
	                nom = currentCase.getObjet().getNom();
	                ctx = currentCase.getObjet().draw(ctx,x,y);
	            }
	        }
	    }
	    ctx = this.drawBoard(ctx);
	    return ctx;
	}

}