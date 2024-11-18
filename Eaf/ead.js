let carro = {
	
	marca: "Ferrari",
	modelo: "F40",
	placa: "abc123",
	ano: 1992,
	cor: "Vermelho",
	quantasPortas: 4,
	assentos:5,
	quantidadePessoas:0,
	
	mudaCor : function(novaCor){
		
		this.cor  = novaCor;
		
	},
	
	obterCor: function(){
		return this.cor;
	},
	
	ObterModelo: function(){
		
		return this.modelo;
	},
	
	ObterMarca: function(){
		
		return this.marca;
	},
	
	obterMarcaModelo: function(){
		
		return "Esse carro é um " + this.marca  + this.modelo;
		
	},
	
	adicionarPessoas: function(numeroPessoas){
		
		if(this.quantidadePessoas >= this.assentos){
			
			return "O carro esta lotado ";
		}
		
		let totalPessoas = this.quantidadePessoas + numeroPessoas;
		
		if(totalPessoas > this.assentos){
			
			let sobra = totalPessoas - this.assentos;
			let cabem = this.assentos - this.quantidadePessoas;
			
			return "Só cabem mais " + cabem + cabem === 1 ? " pessoa " : " Pessoas";
		}
		
		this.quantidadePessoas += numeroPessoas;
		return "Já temos " + this.quantidadePessoas + " pessoas no carro ";
	}
	
};
console.log("Cor atual do carro: " + carro.obterCor()); 

carro.mudaCor("vermelho");
console.log("E agora, qual a cor do carro?  " + carro.obterCor()); 
carro.mudaCor("verde musgo");
console.log("E agora, qual a cor do carro? " +carro.obterCor()); 

console.log(carro.obterMarcaModelo()); 
console.log(carro.adicionarPessoas(2)); 
console.log(carro.adicionarPessoas(4)); 
console.log(carro.adicionarPessoas(1)); 
carro.quantidadePessoas -= 4; 

console.log("Agora temos " + carro.quantidadePessoas + " pessoas no carro."); 

console.log(carro.adicionarPessoas(10));


console.log("Quantidade atual de pessoas no carro: " + carro.quantidadePessoas); 
