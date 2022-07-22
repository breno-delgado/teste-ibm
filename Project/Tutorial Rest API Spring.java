Tutorial Rest API utilizando Spring Boot, criado por Breno Delgado.

Para criação de uma API utilizando Spring Boot.
Primeira coisa que fazemos é a criação do projeto maven no Spring initizlr.
Após isso, abrimos o projeto dentrodo eclipse ou InteliJ. O maven fará a build das dependencias que você escolheu. Costuma ser Spring Dev Tools,
JPA, e o banco de dados utilizado, no caso MySQL, PostGRESQL etc...
Quando abrimos nosso projeto, ele será diviido em um pacote principal onde estará o nomeDoProjetoApplication(onde será rodado o ponto de partida da app).

1º Passo após iniciar a aplicação é verificar o POM.XML e se a msma está inicializando, provável que dê erro devido a falta de config do Banco de dados.
2º Iremos criar nosso package model, para poder distribuir as classes do projeto 
	ex: Cliente.java, Produto.java, Carros.java, etc... Aqui nessa parte devemos criar
os objetos de cada classe, para isso criamos ID do tipo long, e todos objetos privados, para que possamos acessalos via encapsulamento. 

Por se tratar de uma classe de Entidade, ela que emana todos objetos, anotamos acima da class como @Entity do spring. Posteriormente damos atenção ao 
objeto 

@Entity
class Clliente {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(nullable = false, updatable=false)
private Long id; `

}
		
	que devemos sempre dentro da entidade anotar ele como @Id, @GeneratedValue(strategy = GenerationType.AUTO), @Column(nullable=false, updatable=false).
	@Id = significa que é um Id do JavaxPersistence, ele tem RUNtime de target nele e retention.
	@GeneratedValue((strategy = GenerationType.AUTO) = que significa que esse objeto irá gerar valor AUTOMATICO quando for lançado no Endpoint. Ele criar automaticamente um ID.
	@Column(nullable = false, updatable = false) -> Significa que essa coluna qnd criada no BD, nao podera ser NULLA ou ATUALIZADA. FIXA.
	
Criado os objetos das classes, devemos implementar os métodos Gets and Sets, Construtor padrão e construtores individuais. Além do método ToString.

Finalizado as classes de Entidade do nosso package model e seus objetos. Iremos ir para a criação do package repository, para trabalharmos com o o JpaRespository e nossos repositórios.

3º Passo: Criar um package repository, dentro dele criamos uma Interface chamada ClienteRepository.

	public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   //aqui podemos criar métodos personalizados.
   
}
Nesta etapa crie todos os repositórios de cada classe entidade.

4º Passo: Criar nosso package service para trabalharmos com os serviços.

@Service
public class ClienteService {
	@Autowired
    private final ClienteRepository clienteRepository; //Instanciamos nosso ClienteRepository para trabalharmos com os métodos do crud, de find, add, update and delete.

 // segunda opção é criar um construtor e nao usar a anotation @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;  // Aqui foi criado um construtor do nosso clienteRepository;
    }
	
	
	
	
	// Método addCliente, para criar um cliente novo na Service.
    public Cliente addCliente(Cliente cliente){     //Dentro do parâmetro do método Instanciamos a Classe Cliente e declaramos como cliente.
        return clienteRepository.save(employee);   // por não ser void o método, devemos retornar algo, no caso retornamos nosso clienteRepository.save(employee) que criamos acima.
    }

	// Método findAllClientes para buscarmos uma LISTA de todos os clientes cadastrados.
	// Na criação do método devemos colocar como uma List<deClientes> e o nome do método findAllClientes()
    public List<Cliente> findAllClientes(){   
        return clienteRepository.findAll(); //retornamos com nosso repository.findAll();
    }

	// Méotod de atualizar cliente, damos um update em algum dado especifico de um cliente já setado.
    public Cliente updateCliente(Cliente cliente) { //Dentro do parâmetro do método Instanciamos a Classe Cliente e declaramos como cliente.
        return clienteRepository.save(cliente);         //retornamos com nosso repository.save(nossa variavel cliente no parâmetro);
    }

	// Método para buscarmos um cliente pelo seu Id.
    public Employee findByEmployeeById(Long id) {     //aqui será criado uma instancia de CLiente e o nome da variavel, nos parâmetros colocamos o tipo do Id que é Long e a variavel id.
        return clienteRepository.findById(id);   //retormaos com nosso repository e um findById(o nosso id);

    }

	// Método para deletarmos um Cliente pelo seu Id;
    public void deleteEmployee(Long id) {    // declaramos o método como void, pq não irá retornar nada, apenas apagar. Nos parâmetros colocamos o tipo do nosso id e o nome, Long id.
        clienteRepository.delete(id);  //utilizamos nosso repository com.deledeById(id) e o id dentro do parâmetro.
    }

}