$(function() {
	$('.btnInclusaoConsultaModal').on("click",function(event){
		var id = $(this).data('codigo');
		$("#idCliente").val(id);
		$('#inclusaoConsultaModal').modal('show');
	});

	$('#formNewConsulta').submit(function(event) {
		var modal = $(this);
		var form = modal.find('form');
		var action = $('#formNewConsulta').attr('action');
		console.log(action);
		
	    var formData = {
	        'id'    	  : $('input[name=idCliente]').val(),
	        'dataAgenda'  : $('input[name=dataAgenda]').val()
	    };
	
	    $.ajax({
            url: action,
            type: 'POST',
            data: formData,
            success: function (data) {
				console.log(data);
				if(data.retorno == "SUCESSO") {
					/////////alert(data.mensagem);
					$('#inclusaoConsultaModal').modal('hide');	
				} else {
					alert(data.mensagem);
				}
            }
        });	
	    event.preventDefault();
	    window.location.reload();
	});	
});
