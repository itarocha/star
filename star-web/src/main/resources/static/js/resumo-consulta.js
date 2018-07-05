function showModal(event){
	var button = $( event.target );
	if ( button.is( "span" ) ) {
		button = button.closest("button");
	}
	var titulo = button.data('agenda');
	//var titulo = tabelas.get(tabela);
	var indice = button.data('indice');
	var valor = button.data('valor');
	
	$(".modal-title").text("Consulta " + titulo);
	/*
	var tb = buildTabelaPorIndice(tabela, indice);
	
      $("#tabelaCultos").find("tr:gt(0)").remove();
      var botoes = "";
 	 
     for(i = 0; i < tb.length; i++){
    	 var cls = indice == i ? ' class="bg-warning"' : "";  
    	 $("#tabelaCultos > tbody:last-child").append("<tr"+cls+"><td>"+tb[i][0]+"</td><td>"+ tb[i][1]+"</td><td>"+tb[i][2]+"</td></tr>");
     }
     */
	
	/*
		 $.ajax({
	         url: 'api/selecionar',
	         data: {
	             id: id//calEvent.id,
	             //dia: data, //calEvent.start.format(),
	             //hora: titulo//calEvent.title,
	         },
	         success: function(doc) {
	        	 //$("#data_selecionada_id").hide().html(doc.id).fadeIn('fast');
	        	 //$("#data_selecionada_data_culto").hide().html(doc.dataCulto).fadeIn('fast');
	        	 //$("#data_selecionada_horario").hide().html(doc.horario).fadeIn('fast');
	        	 if (acao == "presenca") {
	        		 window.location.replace("sys/kids");	 
	        	 } else
	             if (acao == "detalhes") {
	             	window.location.replace("sys/kids");	 
	             } else
	             if (acao == "estatisticas") {
	             	window.location.replace("sys/kids");	 
	             }
	        	 
	     	 }
	 	});	
	*/
	
     $("#modalConsultaInfo").modal('show');
}

$(function () {
	/*
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

	//calcularIMC();
	//calcularPctGorduraCorporal();
	//calcularPctMuscular();
	//calcularPctGorduraVisceral();
	*/
	
	$(".bt-modal-consulta-info").bind("click", showModal);
});