window.onload = function() {
	$('#calc_button').click(function() {
		calc();
	});
};

//var form = document.getElementById('input');
//form.addEventListener('submit', validateForm, false);

function calc() {

	var f = 0// Number of Generations calculated
	var p = 0// Dominant Allele Frequency
	var q = 0// Recessive Allele Frequency
	var dHom = 0// % of population dominant homozygous
	var dHomSR = 0// dominant homozygous survival rate
	var dHomS = 0// % survived after selection
	var het = 0// % of population heterozygous
	var hetSR = 0// heterozygous survival rate
	var hetS = 0// % survived after selection
	var rHom = 0// % of population recessive homozygous
	var rHomSR = 0// recessive homozygous survival rate
	var rHomS = 0// % survived after selection

	var dHom = $("#dhom")[0].value;
	var dHomSR = $("#sdhom")[0].value;
	var het = $("#het")[0].value;
	var hetSR = $("#shet")[0].value;
	var rHom = $("#rhom")[0].value;
	var rHomSR = $("#srhom")[0].value;
	var gens = $("#gen")[0].value;
	var rep = $("#rep")[0].value;

	var ps = new Array(rep);
	var qs = new Array(rep);

	//Calculate number of animals survived
	dHomS = (dHom * dHomSR);
	hetS = (het * hetSR);
	rHomS = (rHom * rHomSR);

	//Reset frequencies to = 100
	dHom = dHomS * (1 / (dHomS + hetS + rHomS));
	het = hetS * (1 / (dHomS + hetS + rHomS));
	rHom = rHomS * (1 / (dHomS + hetS + rHomS));

	//Calculate allele frequencies
	p = (2 * dHom + het) / (2 * (dHom + het + rHom));
	q = 1 - p;

	ps[0] = p;
	qs[0] = q;

	// ==========================================

	var gen_cur = 2;
	while (gen_cur <= gens) {

		//redistribute HW frequencies
		dHom = p * p;
		het = 2 * p * q;
		rHom = q * q;

		//number survived
		dHomS = (dHom * dHomSR);
		hetS = (het * hetSR);
		rHomS = (rHom * rHomSR);

		// set sum to 100
		dHom = dHomS * (1 / (dHomS + hetS + rHomS));
		het = hetS * (1 / (dHomS + hetS + rHomS));
		rHom = rHomS * (1 / (dHomS + hetS + rHomS));

		//Calculate allele frequencies
		p = (2 * dHom + het) / (2 * (dHom + het + rHom));
		q = 1 - p;

		ps[gen_cur - 1] = p;
		qs[gen_cur - 1] = q;

		if (gen_cur < gens) {

			// print "\n\n Generation {0}:\n".format(generation_current)
			// printout (p,q,dHom,het,rHom)
		} else {
			// print "\n\n Generation {0}:\n".format(f)
			// printout (p,q,dHom,het,rHom)
			// print bar
			// calc = calc + 1 #sets the current calculation # up by one after entire calculation id done
		}
		gen_cur++;
	}

	/*

	 #   Parent Generation

	 #Calculate allele frequencies
	 if print_all == "y":
	 q = ((rHom) ** (.5))
	 p = 1-q

	 print "\n Parent Generation:\n"
	 printout (p,q,dHom,het,rHom)

	 #   First Generation

	 #Calculate number of animals survived
	 dHomS = (dHom * dHomSR)
	 hetS = (het * hetSR)
	 rHomS = (rHom * rHomSR)

	 #Reset frequencies to = 100
	 dHom = dHomS * (1/(dHomS+hetS+rHomS))
	 het = hetS * (1/(dHomS+hetS+rHomS))
	 rHom = rHomS * (1/(dHomS+hetS+rHomS))

	 #Calculate allele frequencies
	 p = (2*dHom + het)/(2*(dHom+het+rHom))
	 q = 1 - p

	 if print_all == "y" or f == 1:
	 print "\n\n Generation 1:\n"
	 printout (p,q,dHom,het,rHom)

	 #  ======================Multi-Generation Calculator:=======================  #

	 #   Loops until current generation is equal to total generations
	 generation_current = 2
	 while generation_current <= f:

	 #redistribute HW frequencies
	 dHom = p*p
	 het = 2*p*q
	 rHom = q*q

	 #number survived
	 dHomS = (dHom * dHomSR)
	 hetS = (het * hetSR)
	 rHomS = (rHom * rHomSR)

	 # set to 100
	 dHom = dHomS * (1/(dHomS+hetS+rHomS))
	 het = hetS * (1/(dHomS+hetS+rHomS))
	 rHom = rHomS * (1/(dHomS+hetS+rHomS))

	 #Calculate allele frequencies
	 p = (2*dHom + het)/(2*(dHom+het+rHom))
	 q = 1 - p

	 if generation_current < f and print_all == "y" :
	 print "\n\n Generation {0}:\n".format(generation_current)
	 printout (p,q,dHom,het,rHom)

	 elif generation_current == f:
	 print "\n\n Generation {0}:\n".format(f)
	 printout (p,q,dHom,het,rHom)
	 print bar
	 calc = calc + 1 #sets the current calculation # up by one after entire calculation id done

	 generation_current += 1
	 */
	plot([ps, qs]);
}

function plot(data) {
	alert(data[0] + " " + data[1]);
	
	var colors = ["#33ff33", "#3366ff"];
	var can, ctx, maxVal, minVal, xScalar, yScalar, numSamples;
	// data sets -- set literally or obtain from an ajax call
	//var ps = [.1, 1, .2, .9, .3, .8, .4, .7, .5, .6, .5, .1, 1, .2, .9, .3, .8, .4, .7, .5, .6, .5];
	//var qs = [.9, 0, .8, .1, .7, .2, .6, .3, .5, .4, .5, .9, 0, .8, .1, .7, .2, .6, .3, .5, .4, .5];

	//data = [ps, qs];
	// var ps = [];
	// var qs = [];

	numSamples = data[0].length;

	var colHead = 50;
	var rowHead = 50;
	var margin = 5;

	function init() {
		// set these values for your data
		height = 400;
		width = 600;
		maxVal = 1;
		minVal = 0;
		var stepSize = 0.1;

		var gens = 15;
		//ps.length;
		var cur_gen = 0;

		can = document.getElementById("can");
		ctx = can.getContext("2d");
		ctx.clearRect(0, 0, width, height);
		ctx.fillStyle = "black"
		ctx.font = "14pt Helvetica";
		ctx.fillStyle = "#0088ff";
		// set vertical scalar to available height / data points
		yScalar = (can.height - colHead - margin) / (maxVal - minVal);
		// set horizontal scalar to available width / number of samples
		xScalar = (can.width - rowHead) / numSamples;

		ctx.strokeStyle = "rgba(128, 128, 255, 0.3)";

		ctx.beginPath();
		// print  column header and draw vertical grid lines
		for ( i = 1; i <= numSamples; i++) {
			var x = i * xScalar;
			ctx.fillText(cur_gen, x, colHead - margin);
			cur_gen++;
			ctx.moveTo(x, colHead);
			ctx.lineTo(x, can.height - margin);
		}
		// print row header and draw horizontal grid lines
		var count = 0;
		for ( scale = maxVal; scale >= minVal; scale -= stepSize) {
			//1 decimal point
			scale = scale.toFixed(1);
			
			var y = colHead + (yScalar * count * stepSize);
			ctx.fillText(scale, margin, y + margin);
			ctx.moveTo(rowHead, y)
			ctx.lineTo(can.width, y)
			count++;
		}
		ctx.stroke();

		// set a color and make one call to plotData()
		// for each data set
		for (var i = 0; i < (data.length); i++) {
			ctx.strokeStyle = colors[i];
			plotData(data[i]);
		}
	}

	function plotData(dataSet) {
		ctx.beginPath();
		ctx.moveTo(colHead + margin, height - dataSet[0] * (height -margin));
		for ( i = 1; i < numSamples; i++) {
			ctx.lineTo(i * xScalar + colHead + 2 * margin, height - dataSet[i] * (height - colHead - margin) - margin);
		}
		ctx.stroke();
	}

	init();
	/*

	 alert("DSadsf");*/

}
