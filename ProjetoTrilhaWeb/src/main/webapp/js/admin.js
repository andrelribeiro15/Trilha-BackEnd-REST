
//Cria o objeto COLDIGO, que será usado como identificador do projeto 
COLDIGO = new Object();

$(document).ready(function () {
	
	COLDIGO.PATH = "/ProjetoTrilhaWeb/rest/";
	
	
    $("header").load("/ProjetoTrilhaWeb/pages/admin/general/header.html");
    $("footer").load("/ProjetoTrilhaWeb/pages/admin/general/footer.html");

	//Função para carregamento de páginas de conteúdo, que 
	//recebe como parametro o nome da pasta com a pagina a ser carregada

	COLDIGO.carregaPagina = function (pagename) {  
		//remove o conteudo criado na abertura de uma janela modal pelo jQueryUI
		
		if($(".ui-dialog"))
			$(".ui-dialog").remove();
		//Limpa a taga section, excluindo todo conteudo de dentro dela. 	
	    $("section").empty();
	    $("section").load(pagename + "/", function (response, status, info) {
	        if (status == "error") {
	            var msg = "Houve um erro ao encontrar a página: " + info.status + " " + info.statusText; $("section").html(msg);
	            $("section").html(msg);
	        }
	    });
	}
	COLDIGO.formatarDinheiro = function(valor){
		return valor.toFixed(2).replace('.',',').replace(/(\d)(?=(\d{3})+\,)/g, "$1.");
		
		
	}
	
	COLDIGO.exibirAviso = function(aviso){
		var modal = {
			title: "Mensagem",
			height: 250,	
			width: 400,
			modal: true,
			buttons: {
			"OK": function(){
				$(this).dialog("close");
				
				}
			}
		};
		$("#modalAviso").html(aviso);
		$("#modalAviso").dialog(modal);
			
	};
	
});