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

	var dHom = parseFloat($("#dhom")[0].value);
	var dHomSR = parseFloat($("#sdhom")[0].value);
	var het = parseFloat($("#het")[0].value);
	var hetSR = parseFloat($("#shet")[0].value);
	var rHom = parseFloat($("#rhom")[0].value);
	var rHomSR = parseFloat($("#srhom")[0].value);
	var gens = parseFloat($("#gen")[0].value);
	//var rep = parseFloat($("#rep")[0].value);
	//var res = parseFloat($("#res")[0].value);

	var ps = new Array(gens);
	var qs = new Array(gens);

	var dHoms = new Array(gens);
	var hets = new Array(gens);
	var rHoms = new Array(gens);

	var nrm = dHom + het + rHom;
	dHom /= nrm;
	het /= nrm;
	rHom /= nrm;
	// alert(dHom + " " + het + " " + rHom);

	var cur_index = 0;
	function logData() {
		ps[cur_index] = p;
		qs[cur_index] = q;
		dHoms[cur_index] = dHoms;
		hets[cur_index] = hets;
		rHoms[cur_index] = rHoms;
		cur_index++;
	}

	//Calculate initial allele frequencies
	p = Math.pow(dHom, 0.5);
	q = 1 - p;

	logData();

	//Calculate number of animals survived
	dHomS = (dHom * dHomSR);
	hetS = (het * hetSR);
	rHomS = (rHom * rHomSR);

	//Normalize frequencies to = 100
	dHom = dHomS * (1 / (dHomS + hetS + rHomS));
	het = hetS * (1 / (dHomS + hetS + rHomS));
	rHom = rHomS * (1 / (dHomS + hetS + rHomS));

	//Calculate allele frequencies
	p = (2 * dHom + het) / (2 * (dHom + het + rHom));
	q = 1 - p;

	logData();

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

		// normalize sum to 100
		dHom = dHomS * (1 / (dHomS + hetS + rHomS));
		het = hetS * (1 / (dHomS + hetS + rHomS));
		rHom = rHomS * (1 / (dHomS + hetS + rHomS));

		//Calculate allele frequencies
		p = (2 * dHom + het) / (2 * (dHom + het + rHom));
		q = 1 - p;

		logData();

		gen_cur++;
	}

	plot([ps, qs]);
}

function clear(canvas) {
	canvas.getContext("2d").clearRect(0, 0, canvas.width, canvas.height);
}

function plot(data) {
	$("#legend").show();
	$("#can").show();
	$("#overlay").show();

	var can = document.getElementById("can");
	var ctx = can.getContext("2d");
	var height = 400;
	var width = 650;

	// alert(data[0] + " " + data[1]);

	var colors = ["#33ff33", "#3366ff"];
	var can, ctx, maxVal, minVal, xScalar, yScalar, numSamples;

	numSamples = data[0].length;

	var colHead = 50;
	var rowHead = 50;
	var margin = 5;

	function init() {
		// set these values for your data

		maxVal = 1;
		minVal = 0;
		var stepSize = 0.1;

		var gens = 15;
		//ps.length;
		var cur_gen = 0;

		can = document.getElementById("can");
		ctx = can.getContext("2d");
		clear(can);
		// ctx.clearRect(0, 0, width, height);
		ctx.fillStyle = "black"
		ctx.font = "14pt Helvetica";
		ctx.fillStyle = "#0088ff";
		// set vertical scalar to available height / data points
		yScalar = (can.height - colHead - margin) / (maxVal - minVal);
		// set horizontal scalar to available width / number of samples
		xScalar = (can.width - rowHead) / (numSamples - 1);

		ctx.strokeStyle = "rgba(128, 128, 255, 0.3)";

		ctx.beginPath();
		// print  column header and draw vertical grid lines
		var increment = 1;
		if (numSamples > 20) {
			increment = Math.floor(numSamples / 20);
		}
		for ( i = 0; i < numSamples; i = i + increment) {
			var x = i * xScalar + colHead;

			ctx.fillText(cur_gen, x - 5, colHead - margin);
			cur_gen += increment;
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
			ctx.moveTo(rowHead - 6, y)
			ctx.lineTo(can.width, y)
			count++;
		}
		ctx.stroke();

		// set a color and make one call to plotData()
		// for each data set
		//go in reverse order so dominant is on top
		for (var i = (data.length) - 1; i >= 0; i--) {
			ctx.strokeStyle = colors[i];
			plotData(data[i]);
		}
	}

	function plotData(dataSet) {
		ctx.beginPath();
		ctx.moveTo(colHead, height - dataSet[0] * (height - colHead - margin) - margin);
		for ( i = 1; i < numSamples; i++) {
			ctx.lineTo(i * xScalar + colHead, height - dataSet[i] * (height - colHead - margin) - margin);
		}
		ctx.stroke();
	}

	init();
	analyzePlot();
	/*

	 alert("DSadsf");*/

}

function analyzePlot() {

	var overlay = document.getElementById("overlay");
	var ctx = overlay.getContext("2d");
	var height = overlay.height;
	var width = overlay.width;
	var inside = false;
	
	//"rgba(128, 255, 255, .4)";

	// Initialization sequence.
	function init() {

		// Attach the mousemove event handler.
		overlay.addEventListener('mousemove', ev_mousemove, false);
		overlay.addEventListener ("mouseout", function() {overlay.width = overlay.width}, false);
	}

	// The mousemove event handler.
	var started = false;
	function ev_mousemove(ev) {
		var x, y;
		//clears
		overlay.width = overlay.width;
		ctx.strokeStyle = "#3366ff";
		
		// Get the mouse position relative to the canvas element.
		if (ev.layerX || ev.layerX == 0) {// Firefox
			x = ev.layerX;
			y = ev.layerY;
		} else if (ev.offsetX || ev.offsetX == 0) {// Opera
			x = ev.offsetX;
			y = ev.offsetY;
		}

		// The event handler works like a drawing pencil which tracks the mouse
		// movements. We start drawing a path made up of lines.
		ctx.moveTo(x, 0);
		ctx.lineTo(x, height);

		ctx.stroke();

	}

	init();

	////

}
