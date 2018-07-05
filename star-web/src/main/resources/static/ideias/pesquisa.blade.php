/* @include('partials.pesquisa',['imprimir'=>'S']) */


<script>
  $(document).ready(function() {
    var p = {!! json_encode($pesquisa) !!};
    var query = {!! json_encode($query) !!};
    var comparacao = [{"display":"Igual a", "name":"igual"},
                      {"display":"Diferente de", "name":"diferente"},
                      {"display":"Semelhante a", "name":"semelhante"},
                      {"display":"Entre", "name":"entre"}
                    ];


    $('#q_campo').on('change', function(e){
      var optionSelected = $("option:selected", this);
      var valueSelected = this.value;
      var index = this.selectedIndex;
      var tipo = p[index].type;

      $("#q_tipo").val(tipo);

      if (tipo == 'date'){
        $("#q_valor_principal").mask("99/99/9999");
        $("#q_valor_complemento").mask("99/99/9999");
      } else {
        $("#q_valor_principal").unmask();
        $("#q_valor_complemento").unmask();

        //$("#q_valor_principal").val("");
        //$("#q_valor_complemento").val("");
      }
    });

    $('#q_operador').on('change', function(e){
      var optionSelected = $("option:selected", this);
      var valueSelected = this.value;
      var index = this.selectedIndex;
      var tipo = comparacao[index].name;

      //alert(tipo);

      if (tipo == 'entre'){
        $("#q_valor_complemento").show();
      } else {
        $("#q_valor_complemento").hide();
      }
    });

    // name, type, display

    // Montagem dos campos de seleção
    var q_campo = $('#q_campo');
    q_campo.html('');
    p.forEach(function(data){
      var option = $('<option></option>');
      q_campo.append(
          option.val(data.name).html(data.display));
          if (data.name == query.campo) {
              option.attr('selected', 'selected');
          }
    }); // end forEach

    // Montagem dos operadores
    var q_operador = $('#q_operador');
    q_operador.html('');
    comparacao.forEach(function(data){
      var option = $('<option></option>');
      q_operador.append(
          option.val(data.name).html(data.display));
          if (data.name == query.operador) {
              option.attr('selected', 'selected');
          }
    }); // end forEach

    $( "#q_campo" ).trigger( "change" );
    $( "#q_operador" ).trigger( "change" );

    // Valor selecionado
    $('#q_valor_principal').val(query.valor_principal);
    $('#q_valor_complemento').val(query.valor_complemento);

    $('#btImprimir').on('click', function(e){
       $("#q_print").val("S");
       $( "#frmPesquisar" ).submit();
    });

  });
</script>

<form id="frmPesquisar" method="GET" class="navbar-form navbar-left" role="search">
  <div class="form-group">
    <input type="hidden" id="q_print" name="q_print">
    <input type="hidden" id="q_tipo" name="q_tipo">
    <select class="form-control" id="q_campo"  name="q_campo"></select>
    <select class="form-control" id="q_operador" name="q_operador"></select>
    <input type="text" class="form-control" placeholder="Conteúdo" id="q_valor_principal" name="q_valor_principal">
    <input type="text" class="form-control" placeholder="Complemento" id="q_valor_complemento" name="q_valor_complemento" style="display:none">
  </div>
  <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Pesquisar</button>
  @if(  isset($imprimir) )
  <button id="btImprimir" class="btn btn-default"><span class="fa fa-file-pdf-o" aria-hidden="true"></span> Imprimir</button>
  @endif
</form>

<!--

public function getCamposPesquisa(){
    return array(
      (object)array('name' => 'tb.nome', 'type' => 'text', 'display' => 'Nome'),
      (object)array('name' => 'tb.cpf', 'type' => 'text', 'display' => 'CPF'),
      (object)array('name' => 'tb.data_nascimento', 'type' => 'date', 'display' => 'Nascimento'),
      (object)array('name' => 'b.nome', 'type' => 'text', 'display' => 'Bairro'),
      (object)array('name' => 'c.nome', 'type' => 'text', 'display' => 'Cidade' ),
      (object)array('name' => 'c.uf', 'type' => 'text', 'display' => 'UF' ),
      (object)array('name' => 'tb.titulo', 'type' => 'text', 'display' => 'Título' ),
      (object)array('name' => 'tb.secao', 'type' => 'text', 'display' => 'Seção' ),
      (object)array('name' => 'tb.zona', 'type' => 'text', 'display' => 'Zona' ),
      (object)array('name' => 'tb.ligou', 'type' => 'text', 'display' => 'Ligou (S/N)' ),
      (object)array('name' => 'u.name', 'type' => 'text', 'display' => 'Usuário que ligou ' ),
        );
  }

-->
