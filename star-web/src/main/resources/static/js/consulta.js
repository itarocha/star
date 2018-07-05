var tabelas = new Map();
tabelas.set(1,"Índice de Massa Corporal");
tabelas.set(2,"% Gordura Corporal");
tabelas.set(3,"% Muscular");
tabelas.set(4,"% Gordura Visceral");

var mapaResultadoImc = new Map();
mapaResultadoImc.set(0,{"descricao": "Peso abaixo do normal", "classe":"btn bt-modal btn-baixo"});
mapaResultadoImc.set(1,{"descricao": "Normal", "classe":"btn bt-modal btn-normal"});
mapaResultadoImc.set(2,{"descricao": "Sobrepeso", "classe":"btn bt-modal btn-medio"});
mapaResultadoImc.set(3,{"descricao": "Obesidade I", "classe":"btn bt-modal btn-muito-alto"});
mapaResultadoImc.set(4,{"descricao": "Obesidade grau II", "classe":"btn bt-modal btn-muito-alto"});
mapaResultadoImc.set(5,{"descricao": "Obesidade mórbida", "classe":"btn bt-modal btn-extremo"});

var mapaResultadoVisceral = new Map();
mapaResultadoVisceral.set(0,{"descricao": "Ideal", "classe":"btn bt-modal btn-baixo"});
mapaResultadoVisceral.set(1,{"descricao": "Normal", "classe":"btn bt-modal btn-normal"});
mapaResultadoVisceral.set(2,{"descricao": "Média", "classe":"btn bt-modal btn-medio"});
mapaResultadoVisceral.set(3,{"descricao": "Alto", "classe":"btn bt-modal btn-muito-alto"});
mapaResultadoVisceral.set(4,{"descricao": "Muito alto (Perigo a saúde)", "classe":"btn bt-modal btn-muito-alto"});
mapaResultadoVisceral.set(5,{"descricao": "Altíssimo (Muito risco para a saúde)", "classe":"btn bt-modal btn-extremo"});

var mapaResultadoGordura = new Map();
mapaResultadoGordura.set(0,{"descricao": "Baixo", "classe":"btn bt-modal btn-baixo"});
mapaResultadoGordura.set(1,{"descricao": "Normal", "classe":"btn bt-modal btn-normal"});
mapaResultadoGordura.set(2,{"descricao": "Alto", "classe":"btn bt-modal btn-alto"});
mapaResultadoGordura.set(3,{"descricao": "Muito alto", "classe":"btn bt-modal btn-muito-alto"});

var resultadoMuscular = new Map();
resultadoMuscular.set(0,{"descricao": "Baixo", "classe":"btn bt-modal btn-baixo"});
resultadoMuscular.set(1,{"descricao": "Normal", "classe":"btn bt-modal btn-normal"});
resultadoMuscular.set(2,{"descricao": "Alto", "classe":"btn bt-modal btn-alto"});
resultadoMuscular.set(3,{"descricao": "Muito alto", "classe":"btn bt-modal btn-muito-alto"});

var arrayImc = [[0,18.5,0], [18.5,25,1], [25,30,2], [30,35,3],[35,40,4],[40,100,5]];
var arrayVisceral = [[1,3,0], [3,5,1], [5,7,2], [7,10,3],[10,12,4],[12,100,5]];

var  mapaPctGordura = new Map();
mapaPctGordura.set("masculino", [{"idades" : [20,40], "valores" : [[0,8,0],[8,20,1],[20,25,2],[25,100,3]]}, 
								 {"idades" : [40,60], "valores" : [[0,11,0],[11,22,1],[22,28,2],[28,100,3]]}, 
								 {"idades" : [60,100], "valores" :[[0,13,0],[13,25,1],[25,30,2],[30,100,3]]} 
]);
mapaPctGordura.set("feminino",[ {"idades" : [20, 40], "valores" : [[0,21,0],[21,33,1],[33,39,2],[39,100,3]]},
								{"idades" : [40, 60], "valores" : [[0,23,0],[23,34,1],[34,40,2],[40,100,3]]},
								{"idades" : [60, 100], "valores" : [[0,24,0],[24,36,1],[36,42,2],[42,100,3]]}
]);

var  mapaPctMuscular = new Map();
mapaPctMuscular.set("feminino",[ {"idades" : [18, 40], "valores" :  [[0,24.3,0],[24.3,30.4,1],[30.4,35.4,2],[35.4,100,3]]},
	 							 {"idades" : [40, 60], "valores" :  [[0,24.1,0],[24.1,30.2,1],[30.2,35.2,2],[35.2,100,3]]},
	 							 {"idades" : [60, 100], "valores" : [[0,23.9,0],[23.9,30.0,1],[30.0,35.0,2],[35.0,100,3]]}
]);
mapaPctMuscular.set("masculino", [{"idades" : [18,40], "valores" : [[0,33.3,0],[33.3,39.4,1],[39.4,44.1,2],[44.1,100,3]]}, 
								  {"idades" : [40,60], "valores" : [[0,33.1,0],[33.1,39.2,1],[39.2,43.9,2],[43.9,100,3]]}, 
								  {"idades" : [60,100], "valores" :[[0,32.9,0],[32.9,39.0,1],[39.0,43.7,2],[43.7,100,3]]} 
]);

function conserta(valor){
	return parseFloat(valor.replace(',','.').replace(' ',''));
}

function trucaPontoPorVirgula(valor){
	return valor.replace('.',',').replace(' ','');
}

function calcularIMC(){
	var peso = conserta($('#peso').val());
	var altura = conserta($('#altura').val());
	var valor = peso / (altura * altura);
	var valorFmt = trucaPontoPorVirgula(valor.toFixed(2));
	$('#indiceMassaCorporal').val(valorFmt);
	$("#bt-indicemassacorporal").removeClass();
	for (x = 0; x < arrayImc.length; x++){
		if ((valor >= arrayImc[x][0]) && ((valor < arrayImc[x][1]))) {
			var indice = arrayImc[x][2];
			var resultado = mapaResultadoImc.get(indice);
			$("#bt-indice-massa-corporal").removeClass().addClass(resultado.classe);
			$("#bt-indice-massa-corporal").data("indice",indice);
			$("#bt-indice-massa-corporal").data("valor",valorFmt);
			break;
		}
	}
}

function calcularPctGorduraCorporal(){
	var valor = conserta($('#pctGorduraCorporal').val());
	var idade = conserta($('#idade').val());
	var sexo = $('#sexo').val().toLowerCase();

	var faixaValor = buscarFaixaPctGorduraCorporal(valor, idade, sexo);
	var indice = faixaValor[2];
	var classe = mapaResultadoGordura.get(indice);
	$("#bt-pct-gordura-corporal").removeClass().addClass(classe.classe);
	$("#bt-pct-gordura-corporal").data("indice",indice);
	$("#bt-pct-gordura-corporal").data("valor",trucaPontoPorVirgula(valor.toFixed(2)));
}

function buscarFaixaPctGorduraCorporal(valor, idade, sexo){
	var faixaIdade = buildTabelaPctGorduraCorporal(idade, sexo);
	for (z = 0; z < faixaIdade.length; z++){
		faixaValor = faixaIdade[z];
		if ((valor >= faixaValor[0]) && (valor < faixaValor[1])) {
			return faixaValor;
		}
	}
}

function buildTabelaPctGorduraCorporal(idade, sexo){
	var tb = mapaPctGordura.get(sexo);
	for (y = 0; y < tb.length; y++){
		faixaIdade = tb[y];
		if ( (idade >= faixaIdade.idades[0]) && (idade < faixaIdade.idades[1] ) )
		{
			return faixaIdade.valores;
			break;
		}
	}
}

function calcularPctMuscular(){
	var valor = conserta($('#pctMuscular').val());
	var idade = conserta($('#idade').val());
	var sexo = $('#sexo').val().toLowerCase();

	var faixaValor = buscarFaixaPctMuscular(valor, idade, sexo);
	var indice = faixaValor[2];
	var classe = resultadoMuscular.get(indice);
	$("#bt-pct-muscular").removeClass().addClass( classe.classe );
	$("#bt-pct-muscular").data("indice",indice);
	$("#bt-pct-muscular").data("valor",trucaPontoPorVirgula(valor.toFixed(2)));
}

function buscarFaixaPctMuscular(valor, idade, sexo){
	var faixaIdade = buildTabelaPctMuscular(idade, sexo);
	for (z = 0; z < faixaIdade.length; z++){
		faixaValor = faixaIdade[z];
		if ((valor >= faixaValor[0]) && (valor < faixaValor[1])) {
			return faixaValor;
		}
	}
}

function buildTabelaPctMuscular(idade, sexo){
	var tb = mapaPctMuscular.get(sexo);
	for (y = 0; y < tb.length; y++){
		faixaIdade = tb[y];
		if ( (idade >= faixaIdade.idades[0]) && (idade < faixaIdade.idades[1] ) )
		{
			return faixaIdade.valores;
			break;
		}
	}
}

function calcularPctGorduraVisceral(){
	var valor = conserta($('#pctGorduraVisceral').val());
	for (x = 0; x < arrayVisceral.length; x++){
		if ((valor >= arrayVisceral[x][0]) && ((valor < arrayVisceral[x][1]))) {
			var indice = arrayImc[x][2];
			var resultado = mapaResultadoVisceral.get(indice);
			$("#bt-pct-gordura-visceral").removeClass().addClass(resultado.classe);
			$("#bt-pct-gordura-visceral").data("indice",indice);
			$("#bt-pct-gordura-visceral").data("valor",trucaPontoPorVirgula(valor.toFixed(2)));
			break;
		}
	}
}

function showModal(event){
	var button = $( event.target );
	if ( button.is( "span" ) ) {
		button = button.closest("button");
	}
	var tabela = button.data('tabela');
	var titulo = tabelas.get(tabela);
	var indice = button.data('indice');
	var valor = button.data('valor');
	
	$(".modal-title").text(titulo + ": " + valor);
	
	var tb = buildTabelaPorIndice(tabela, indice);
	
      $("#tabelaCultos").find("tr:gt(0)").remove();
      var botoes = "";
 	 
     for(i = 0; i < tb.length; i++){
    	 var cls = indice == i ? ' class="bg-warning"' : "";  
    	 $("#tabelaCultos > tbody:last-child").append("<tr"+cls+"><td>"+tb[i][0]+"</td><td>"+ tb[i][1]+"</td><td>"+tb[i][2]+"</td></tr>");
     } 
     $("#myModal").modal('show');
}

function buildTabelaPorIndice(tipoTabela, indice){
	var idade = conserta($('#idade').val());
	var sexo = $('#sexo').val().toLowerCase();

	var retorno = [];
	// Índice de Massa Corporal
	if (tipoTabela == 1) {
		for (x = 0; x < arrayImc.length; x++){
			retorno.push([arrayImc[x][0],arrayImc[x][1], mapaResultadoImc.get(arrayImc[x][2]).descricao]);
		}	
	// Gordura Corporal 	
	} else if (tipoTabela == 2) {
		var tabela = buildTabelaPctGorduraCorporal(idade, sexo);
		for (x = 0; x < tabela.length; x++){
			retorno.push([tabela[x][0],tabela[x][1], mapaResultadoGordura.get(tabela[x][2]).descricao]);
		}
	// Muscular		
	} else if (tipoTabela == 3) {
		var tabela = buildTabelaPctMuscular(idade, sexo);
		for (x = 0; x < tabela.length; x++){
			retorno.push([tabela[x][0],tabela[x][1], resultadoMuscular.get(tabela[x][2]).descricao]);
		}
	// Gordura Visceral	
	} else if (tipoTabela == 4) {
		for (x = 0; x < arrayVisceral.length; x++){
			retorno.push([arrayVisceral[x][0],arrayVisceral[x][1], mapaResultadoVisceral.get(arrayVisceral[x][2]).descricao]);
		}	
	} 
	return retorno;
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
	
	$(".bt-modal").bind("click", showModal);
});