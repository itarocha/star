var labels = [];
var peso = [];
var pctGorduraCorporal = [];
var indiceMassaCorporal = [];
var pctMuscular = [];
var pctGorduraVisceral = [];

var optionsPeso = {
		  maintainAspectRatio: true,
		  responsive: true,
		  legend: {display: false},
		  elements: {
			  line: {
				  tension: 0,
			  }
		  },
		  scales: {
		    xAxes: [{
		      gridLines: {
		        display: false
		      }
		    }],
		    yAxes:[{
		    	ticks: {
		    		suggestedMin: 50,
		    		suggestedMax: 100
		    	}
		    }]
		  }
		};

var optionsMassa = {
		  maintainAspectRatio: true,
		  responsive: true,
		  legend: {display: false},
		  elements: {
			  line: {
				  tension: 0,
			  }
		  },
		  scales: {
		    xAxes: [{
		      gridLines: {
		        display: false
		      }
		    }],
		    yAxes:[{
		    	ticks: {
		    		suggestedMin: 0,
		    		suggestedMax: 50
		    	}
		    }]
		  }
		};

var optionsGorduraVisceral = {
		  maintainAspectRatio: true,
		  responsive: true,
		  legend: {display: false},
		  elements: {
			  line: {
				  tension: 0,
			  }
		  },
		  scales: {
		    xAxes: [{
		      gridLines: {
		        display: false
		      }
		    }],
		    yAxes:[{
		    	ticks: {
		    		suggestedMin: 0,
		    		suggestedMax: 10
		    	}
		    }]
		  }
		};

// http://www.chartjs.org/docs/latest/
function buildChartPeso() {
	var data = {
			  labels: labels,
			  datasets: [{
			    label: "Peso (kg)",
			    backgroundColor: "rgba(255,99,132,0.5)",
			    borderColor: "rgba(255,99,132,1)",
			    borderWidth: 2,
			    hoverBackgroundColor: "rgba(255,99,132,0.6)",
			    hoverBorderColor: "rgba(255,99,132,1)",
			    data: peso,
			  }]
	};

	var ctx = $("#chartPeso");

	var grafico = new Chart(ctx, {
		type: 'line',
		options : optionsPeso,
		data: data
	});
}

function buildChartGorduraCorporal() {
	var data = {
			  labels: labels,
			  datasets: [{
			    label: "Gordura Corporal (%)",
			    backgroundColor: "rgba(204,0,204,0.5)",
			    borderColor: "rgba(204,0,204,1)",
			    borderWidth: 2,
			    hoverBackgroundColor: "rgba(204,0,204,0.6)",
			    hoverBorderColor: "rgba(204,0,204,1)",
			    data: pctGorduraCorporal,
			  }]
	};

	var ctx = $("#chartGorduraCorporal");

	var grafico = new Chart(ctx, {
		type: 'line',
		options : optionsMassa,
		data: data
	});
}

function buildChartMassaCorporal() {
	var data = {
			  labels: labels,
			  datasets: [{
			    label: "Massa Corporal (%)",
			    backgroundColor: "rgba(0, 64, 255,0.5)",
			    borderColor: "rgba(0, 64, 255,1)",
			    borderWidth: 2,
			    hoverBackgroundColor: "rgba(0, 64, 255,0.6)",
			    hoverBorderColor: "rgba(0, 64, 255,1)",
			    data: indiceMassaCorporal,
			  }]
	};

	var ctx = $("#chartMassaCorporal");

	var grafico = new Chart(ctx, {
		type: 'line',
		options : optionsMassa,
		data: data
	});
}

function buildChartMuscular() {
	var data = {
			  labels: labels,
			  datasets: [{
			    label: "Índice Muscular (%)",
			    backgroundColor: "rgba(255, 204, 0,0.5)",
			    borderColor: "rgba(255, 204, 0,1)",
			    borderWidth: 2,
			    hoverBackgroundColor: "rgba(255, 204, 0,0.6)",
			    hoverBorderColor: "rgba(255, 204, 0,1)",
			    data: pctMuscular,
			  }]
	};

	var ctx = $("#chartMuscular");

	var grafico = new Chart(ctx, {
		type: 'line',
		options : optionsMassa,
		data: data
	});
}

function buildChartGorduraVisceral() {
	var data = {
			  labels: labels,
			  datasets: [{
			    label: "Gordura Visceral (%)",
			    backgroundColor: "rgba(0,204,0,0.5)",
			    borderColor: "rgba(0,204,0,1)",
			    borderWidth: 2,
			    hoverBackgroundColor: "rgba(0,204,0,0.6)",
			    hoverBorderColor: "rgba(0,204,0,1)",
			    data: pctGorduraVisceral,
			  }]
	};

	var ctx = $("#chartGorduraVisceral");

	var grafico = new Chart(ctx, {
		type: 'line',
		options : optionsGorduraVisceral,
		data: data
	});
}

function buildGraficos(graficoData){
	for(var i in graficoData) {
		labels.push(graficoData[i].dataConsultaFmt);
		peso.push(graficoData[i].peso);
		pctGorduraCorporal.push(graficoData[i].pctGorduraCorporal);
		indiceMassaCorporal.push(graficoData[i].indiceMassaCorporal);
		pctMuscular.push(graficoData[i].pctMuscular);
		pctGorduraVisceral.push(graficoData[i].pctGorduraVisceral);
	}
	buildChartPeso();
	buildChartGorduraCorporal();
	buildChartMassaCorporal();
	buildChartMuscular();
	buildChartGorduraVisceral();
}


