/*	
 
 var data = [[1,12],[2,7],[3,6],[4,6],[5,9],[6,13],[7,12],[8,15],[9,14],[10,18]];
	var dataPoints = [];
	$.getJSON("https://canvasjs.com/services/data/datapoints.php?xstart=1&ystart=10&length=100&type=json", function(data) {  
	    $.each(data, function(key, value){
	        dataPoints.push({x: value[0], y: parseInt(value[1])});
	    });
	    var chart = new CanvasJS.Chart("chartContainer",{
	        title:{
	            text:"Rendering Chart with dataPoints from External JSON"
	        },
	        data: [{
	        type: "line",
	            dataPoints : dataPoints,
	        }]
	    });
	    chart.render();
	});	
*/










var listaPctGordura = 
 {"data":[	{"sexo": "masculino", "idadeIni": 20, "idadeFim": 40, "valorIni":  0, "valorFim":    8, "classe" : "btn btn-baixo"},
			{"sexo": "masculino", "idadeIni": 20, "idadeFim": 40, "valorIni":  8, "valorFim":   20, "classe" : "btn btn-normal"},
			{"sexo": "masculino", "idadeIni": 20, "idadeFim": 40, "valorIni": 20, "valorFim":   25, "classe" : "btn btn-alto"},
			{"sexo": "masculino", "idadeIni": 20, "idadeFim": 40, "valorIni": 25, "valorFim": 1000, "classe" : "btn btn-muito-alto"},

			{"sexo": "masculino", "idadeIni": 40, "idadeFim": 60, "valorIni":  0, "valorFim":   11, "classe" : "btn btn-baixo"},
			{"sexo": "masculino", "idadeIni": 40, "idadeFim": 60, "valorIni": 11, "valorFim":   22, "classe" : "btn btn-normal"},
			{"sexo": "masculino", "idadeIni": 40, "idadeFim": 60, "valorIni": 22, "valorFim":   28, "classe" : "btn btn-alto"},
			{"sexo": "masculino", "idadeIni": 40, "idadeFim": 60, "valorIni": 28, "valorFim": 1000, "classe" : "btn btn-muito-alto"},
			
			{"sexo": "masculino", "idadeIni": 60, "idadeFim": 80, "valorIni":  0, "valorFim":   13, "classe" : "btn btn-baixo"},
			{"sexo": "masculino", "idadeIni": 60, "idadeFim": 80, "valorIni": 13, "valorFim":   25, "classe" : "btn btn-normal"},
			{"sexo": "masculino", "idadeIni": 60, "idadeFim": 80, "valorIni": 25, "valorFim":   30, "classe" : "btn btn-alto"},
			{"sexo": "masculino", "idadeIni": 60, "idadeFim": 80, "valorIni": 30, "valorFim": 1000, "classe" : "btn btn-muito-alto"},			

			
			
			{"sexo": "feminino", "idadeIni": 20, "idadeFim": 40, "valorIni":  0, "valorFim":   21, "classe" : "btn btn-baixo"},
			{"sexo": "feminino", "idadeIni": 20, "idadeFim": 40, "valorIni": 21, "valorFim":   33, "classe" : "btn btn-normal"},
			{"sexo": "feminino", "idadeIni": 20, "idadeFim": 40, "valorIni": 33, "valorFim":   39, "classe" : "btn btn-alto"},
			{"sexo": "feminino", "idadeIni": 20, "idadeFim": 40, "valorIni": 39, "valorFim": 1000, "classe" : "btn btn-muito-alto"},

			{"sexo": "feminino", "idadeIni": 40, "idadeFim": 60, "valorIni":  0, "valorFim":   23, "classe" : "btn btn-baixo"},
			{"sexo": "feminino", "idadeIni": 40, "idadeFim": 60, "valorIni": 23, "valorFim":   35, "classe" : "btn btn-normal"},
			{"sexo": "feminino", "idadeIni": 40, "idadeFim": 60, "valorIni": 35, "valorFim":   40, "classe" : "btn btn-alto"},
			{"sexo": "feminino", "idadeIni": 40, "idadeFim": 60, "valorIni": 40, "valorFim": 1000, "classe" : "btn btn-muito-alto"},
			
			{"sexo": "feminino", "idadeIni": 60, "idadeFim": 80, "valorIni":  0, "valorFim":   24, "classe" : "btn btn-baixo"},
			{"sexo": "feminino", "idadeIni": 60, "idadeFim": 80, "valorIni": 24, "valorFim":   36, "classe" : "btn btn-normal"},
			{"sexo": "feminino", "idadeIni": 60, "idadeFim": 80, "valorIni": 36, "valorFim":   42, "classe" : "btn btn-alto"},
			{"sexo": "feminino", "idadeIni": 60, "idadeFim": 80, "valorIni": 42, "valorFim": 1000, "classe" : "btn btn-muito-alto"}			

			]};
 
 // (sexo == sexo) && (idade >= idadeIni) && (idade < idadeFim) && (valor >= valorIni) && (valor < valorFim)

function conserta(valor){
	return parseFloat(valor.replace(',','.').replace(' ',''))
}

function trucaPontoPorVirgula(valor){
	return valor.replace('.',',').replace(' ','')
}

function calcularPctGorduraCorporal(){
	var valor = conserta($('#pctGorduraCorporal').val());
	var idade = conserta($('#idade').val());
	var sexo = $('#sexo').val().toLowerCase();
	console.log(idade);
	console.log(sexo);
	$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-default');
	
	 for (i = 0; i < listaPctGordura.data.length; i++){
		 if ((sexo == listaPctGordura.data[i].sexo) && 
		    (idade >= listaPctGordura.data[i].idadeIni) && 
		    (idade < listaPctGordura.data[i].idadeFim) && 
		    (valor >= listaPctGordura.data[i].valorIni) && 
		    (valor < listaPctGordura.data[i].valorFim)) 
		 {
			 $("#bt-pct-gordura-corporal").removeClass().addClass( listaPctGordura.data[i].classe );		 
		 }
	 }
	
	
	
	
	/*
	if (sexo == 'masculino'){
	
		if ((idade >= 20) && (idade < 40) && (valor < 8)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 20) && (idade < 40) && (valor >= 8) && (valor < 20)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 20) && (idade < 40) && (valor >= 20) && (valor < 25)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 20) && (idade < 40) && (valor > 25)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		} else

		if ((idade >= 40) && (idade < 60) && (valor < 11)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 40) && (idade < 60) && (valor >= 11) && (valor < 22)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 40) && (idade < 60) && (valor >= 22) && (valor < 28)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 40) && (idade < 60) && (valor > 28)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		} else
		
		if ((idade >= 60) && (valor < 13)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 60) && (valor >= 13) && (valor < 24)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 60) && (valor >= 24) && (valor < 30)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 60) && (valor > 30)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		}
		
	} else if (sexo == 'feminino'){
		
		if ((idade >= 20) && (idade < 40) && (valor < 21)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 20) && (idade < 40) && (valor >= 21) && (valor < 33)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 20) && (idade < 40) && (valor >= 33) && (valor < 38)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 20) && (idade < 40) && (valor > 39)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		} else

		if ((idade >= 40) && (idade < 60) && (valor < 23)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 40) && (idade < 60) && (valor >= 23) && (valor < 34)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 40) && (idade < 60) && (valor >= 35) && (valor < 40)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 40) && (idade < 60) && (valor > 40)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		} else
		
		if ((idade >= 60) && (valor < 24)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-baixo');
		} else if ((idade >= 60) && (valor >= 24) && (valor < 36)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-normal');
		} else  if ((idade >= 60) && (valor >= 36) && (valor < 42)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-alto');
		} else  if ((idade >= 60) && (valor > 42)){
			$("#bt-pct-gordura-corporal").removeClass().addClass('btn btn-muito-alto');
		}
	}
	*/
}

function calcularPctMuscular(){
	
}

function calcularPctGorduraVisceral(){
	
}

function calcularIMC(){
	var peso = conserta($('#peso').val());
	var altura = conserta($('#altura').val());
	var imc = peso / (altura * altura);
	$('#indiceMassaCorporal').val(trucaPontoPorVirgula(imc.toFixed(2)));
	$("#bt-indicemassacorporal").removeClass();
	if (imc < 18.5) {
		$("#bt-indicemassacorporal").addClass('btn btn-baixo');
	} else if ((imc >= 18.5) && (imc < 25)) {
		$("#bt-indicemassacorporal").addClass('btn btn-normal');
	} else if ((imc >= 25) && (imc < 30)) {
		$("#bt-indicemassacorporal").addClass('btn btn-medio');
	} else if ((imc >= 30) && (imc < 35)) {
		$("#bt-indicemassacorporal").addClass('btn btn-alto');
	} else if ((imc >= 35) && (imc < 40)) {
		$("#bt-indicemassacorporal").addClass('btn btn-muito-alto');
	} else {
		$("#bt-indicemassacorporal").addClass('btn btn-extremo');
	}
}

$(function () {
	$("#peso").change(function () {
		calcularIMC();
	});
	$("#pctGorduraCorporal").change(function () {
		calcularPctGorduraCorporal();
	});
	$("#pctMuscular").change(function () {
		calcularPctMuscular();
	});
	$("#pctGorduraVisceral").change(function () {
		calcularPctGorduraVisceral();
	});

	$("#peso").focus();
	$(".formatoData").mask("99/99/9999");

	calcularIMC();
	calcularPctGorduraCorporal();
	calcularPctMuscular();
	calcularPctGorduraVisceral();
});
