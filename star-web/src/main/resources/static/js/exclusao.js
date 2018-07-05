$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	var button = $(event.relatedTarget);
	var id = button.data('codigo');
	//var acao = button.data('acao')+id;
	var descricao = button.data('descricao');
	var modal = $(this);
	var form = modal.find('form');
	//var action = form.data('url-base');
	var action = form.attr('action');
	if (!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + id);
	
	modal.find('.modal-body span').html('Deseja realmente excluir "<strong>'+descricao+'"</strong> ?');
});
