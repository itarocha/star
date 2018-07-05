var linha = -1;
var camposPesquisa = null;
var query = null;
var comparacao = [	{"display":"Igual a", "name":"igual"},
					{"display":"Diferente de", "name":"diferente"},
					{"display":"Semelhante a", "name":"semelhante"},
					{"display":"Entre", "name":"entre"}];

function configurarFiltros(_camposPesquisa, _query){
	camposPesquisa = _camposPesquisa;
	query = _query;
}

$(function() {
	//var rowCount = $(".linha-filtro").length;
    filtro = query.filtro;
    
	if (filtro.length > 0) {
        query.filtro.forEach(function(data){
        	buildFiltros(data);
    	});
    } else {
    	buildFiltros();	
    }
    
    $(".adicionar-new").click(function(e) {
  		e.preventDefault();
		buildFiltros();      	  
	}); 
	
});

function buildFiltros(filtro){
	linha++; // = $(".linha-filtro").length;

	theDiv = $('<div class="row linha-filtro"></div>');
	theDiv.insertBefore('.linha-pesquisa');

	inputTipo = $('<input type="hidden" class="input-visible" id="filtro-'+linha+'-visible" name="filtro['+linha+'].visible" value="true"></input>')
	inputTipo.appendTo(theDiv);

	inputTipo = $('<input type="hidden" class="input-tipo" id="filtro-'+linha+'-tipo" name="filtro['+linha+'].tipo"></input>')
	inputTipo.appendTo(theDiv);

	labelCampo = $('<span class="input-group-addon" id="label-filtro['+linha+'].campo" data-linha='+linha+'>Campo:</span>');
	selectCampo = $('<select class="form-control select-campo" id="filtro-'+linha+'-campo" data-linha='+linha+' name="filtro['+linha+'].campo" aria-describedby="label-filtro['+linha+'].campo"></select>');
	$('<div id="div-select-campo'+linha+'" class="col-md-3"></div>').append($('<div class="input-group"></div>').append(labelCampo).append(selectCampo)).appendTo(theDiv);

	selectOperador = $('<select class="form-control select-operador" id="filtro-'+linha+'-operador" data-linha='+linha+' name="filtro['+linha+'].operador" aria-describedby="label-filtro['+linha+'].operador"></select>');
	$('<div class="col-md-2"></div>').append(selectOperador).appendTo(theDiv);

	labelExpressao = $('<span class="input-group-addon" id="label-filtro['+linha+'].expressao">Expressão:</span>');
	inputExpressao = $('<input type="text" class="form-control input-expressao" placeholder="Conteúdo" id="filtro-'+linha+'-expressao" data-linha='+linha+' name="filtro['+linha+'].expressao" aria-describedby="addon_valor_principal['+linha+']"></input>');
	$('<div id="div-input-expressao'+linha+'" class="col-md-3"></div>').append($('<div class="input-group"></div>').append(labelExpressao).append(inputExpressao)).appendTo(theDiv);

	labelComplemento = $('<span class="input-group-addon label-complemento" id="label-filtro['+linha+'].complemento" data-linha='+linha+'>Complemento:</span>');
	inputComplemento = $('<input type="text" class="form-control input-complemento" placeholder="Complemento" id="filtro-'+linha+'-complemento" data-linha='+linha+' name="filtro['+linha+'].complemento" aria-describedby="label-filtro['+linha+'].complemento"></input>');
	$('<div id="div-input-complemento'+linha+'" class="col-md-3"></div>').append($('<div class="input-group"></div>').append(labelComplemento).append(inputComplemento)).appendTo(theDiv);

	labelSelectExpressao = $('<span class="input-group-addon label-select-expressao" id="label-filtro['+linha+'].expressaolista" data-linha='+linha+'>Expressão:</span>');
	selectExpressao = $('<select class="form-control select-expressao" id="filtro-'+linha+'-expressaolista" data-linha='+linha+' name="filtro['+linha+'].expressaolista" aria-describedby="label-filtro['+linha+'].expressaolista"></select>');
	$('<div id="div-select-expressao'+linha+'" class="col-md-3"></div>').append($('<div class="input-group"></div>').append(labelSelectExpressao).append(selectExpressao)).appendTo(theDiv);

	labelSelectComplemento = $('<span class="input-group-addon label-select-complemento" id="label-filtro['+linha+'].complementolista" data-linha='+linha+'>Complemento:</span>');
	selectComplemento = $('<select class="form-control select-complemento" id="filtro-'+linha+'-complementolista" data-linha='+linha+' name="filtro['+linha+'].complementolista" aria-describedby="label-filtro['+linha+'].complementolista"></select>');
	$('<div id="div-select-complemento'+linha+'" class="col-md-3"></div>').append($('<div class="input-group"></div>').append(labelSelectComplemento).append(selectComplemento)).appendTo(theDiv);

	buttonRemove = $('<button type="button" class="btn btn-danger btremove" id="btremove'+linha+'" data-linha='+linha+'><i class="glyphicon glyphicon-trash icon-white"></i></button>');
	$('<div id="div-btremove'+linha+'" class="col-md-1"></div>').append(buttonRemove).appendTo(theDiv);

	$(".btremove").bind("click", function(e){
		e.preventDefault();
		$(this).closest(".linha-filtro").remove();
	});	

	if (filtro != null) {
		if (undefined != filtro.expressao) {
			inputExpressao.val(filtro.expressao);
		}
	}

	if (filtro != null){
		if (undefined != filtro.complemento){
			inputComplemento.val(filtro.complemento);
		}
	}

	montarOperadores(filtro, selectOperador);
	montarCampos(filtro, selectCampo);
} // end buildFiltros

function montarCampos(filtro, selectCampo){
	// Montagem dos campos de seleção
	//var q_campo = selectCampo;
	selectCampo.html('');
	camposPesquisa.forEach(function(data){
		var tipo = data.type;
		var option = $('<option></option>');
		selectCampo.append(option.val(data.name).html(data.display));
		option.attr('data-type',tipo);
		if (filtro != undefined){
			if (data.name == filtro.campo) {
				option.attr('selected', 'selected');
			}
		}
	}); // end forEach

	selectCampo.on('change', function(e){
		var elemento = e.target;
		var index = $(elemento).data("linha");
		
		var selectedIndex = elemento.selectedIndex;
		var inputTipo = $('#filtro-'+index+'-tipo'); 
		var inputExpressao = $('#filtro-'+index+'-expressao'); 
		var inputComplemento = $('#filtro-'+index+'-complemento'); 
		var selectExpressao = $('#filtro-'+index+'-expressaolista');
		var selectComplemento = $('#filtro-'+index+'-complementolista');
		
		var  _typeCampo =  $("option:selected", selectCampo).data('type');
		inputTipo.val(_typeCampo);

		if (_typeCampo == 'date'){
			inputExpressao.mask("99/99/9999");
			inputComplemento.mask("99/99/9999");
		} else 
		if (_typeCampo == 'time'){
			inputExpressao.mask("99:99");
			inputComplemento.mask("99:99");
		} else 
		{
			inputExpressao.unmask();
			inputComplemento.unmask();
		}

		// Verifica se o campo contém lista de valores
		if (camposPesquisa[selectedIndex].values.length > 0) {
			var valores = camposPesquisa[selectedIndex].values;
			valores.forEach(function(data){
				var option = $('<option></option>');
				selectExpressao.append(option.val(data.option).html(data.value));
				if (filtro != undefined){
					if (data.option == filtro.expressaolista) {
						option.attr('selected', 'selected');
					}
				}
			}); // end forEach

			valores.forEach(function(data){
				var option = $('<option></option>');
				selectComplemento.append(option.val(data.option).html(data.value));
				if (filtro != undefined){
					if (data.option == filtro.complementolista) {
						option.attr('selected', 'selected');
					}
				}
			}); // end forEach
		}
		configurarVisibilidade(e);
	}); // end q_campo change
	selectCampo.trigger( "change" );
} // end montarCampos

// Montagem dos campos operadores
function montarOperadores(filtro, selectOperador){
	selectOperador.html('');
	comparacao.forEach(function(data){
		var option = $('<option></option>');
		selectOperador.append(option.val(data.name).html(data.display));
		if (filtro != undefined) {
			if (data.name == filtro.operador) {
				option.attr('selected', 'selected');
			}
		}
	}); // end forEach
	selectOperador.on('change', function(e){
		configurarVisibilidade(e);
	}); // end on change

	selectOperador.trigger( "change" );
} // end montarOperadores

function configurarVisibilidade(e){
	var elemento = e.target;
	var index = $(elemento).data("linha");

	var _divInputExpressao =  $("#div-input-expressao"+linha);
	var _divInputComplemento = $("#div-input-complemento"+linha);
	var _divSelectExpressao = $("#div-select-expressao"+linha);
	var _divSelectComplemento = $("#div-select-complemento"+linha);
	var _selectCampo = $("#filtro-"+linha+"-campo");
	var _selectOperador = $("#filtro-"+linha+"-operador");

	if (index >= 0){
		var _typeCampo =  $("option:selected", _selectCampo).data('type');

		_divInputExpressao.hide();
		_divInputComplemento.hide();
		_divSelectExpressao.hide();
		_divSelectComplemento.hide();

		var campoIndex = _selectOperador[0].selectedIndex;
		var operador = comparacao[campoIndex].name;

		if (operador == "entre") {
			if (_typeCampo == "list") {
				_divSelectExpressao.show();
				_divSelectComplemento.show();
			} else {
				_divInputExpressao.show();
				_divInputComplemento.show();
			}
		} else {
			if (_typeCampo == 'list') {
				_divSelectExpressao.show();
				_divSelectComplemento.hide();
			} else {
				_divInputComplemento.hide();
				_divInputExpressao.show();
			}
		}
	}
}