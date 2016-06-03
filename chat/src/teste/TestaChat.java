package teste;

import hibernate.GenericWorker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.Acesso;
import modelo.Conversa;
import modelo.ConversaID;
import modelo.Convite;
import modelo.ConviteID;
import modelo.Empregado;
import modelo.Endereco;
import modelo.Internauta;
import modelo.Medico;
import modelo.Mensagem;
import modelo.MensagemID;
import modelo.Municipio;
import modelo.Paciente;
import modelo.Pessoa;

import org.hibernate.criterion.Criterion;

public class TestaChat {
	private static final int QTDE = 1;

	public static void testaMunicipio() throws Throwable {
		GenericWorker<Municipio, Integer> DAO2 = new GenericWorker<Municipio, Integer>(
				Municipio.class);
		System.out.println("INICIO DE MUNICÍPIO");
		for (int i = 0; i < QTDE; i++) {
			Municipio municipioPOJO = new Municipio();
			municipioPOJO.setIdMunicipio(1000 + i);
			municipioPOJO.setNomeMunicipio("Brasília");
			municipioPOJO.setUfMunicipio("DF");
			municipioPOJO.setHabitantes(5345 + i);
			if (DAO2.inclui(municipioPOJO))
				System.out.println("Inserido: " + municipioPOJO);
			// ----------------------------------------
			municipioPOJO.setNomeMunicipio("Brasília" + i);
			if (DAO2.altera(municipioPOJO))
				System.out.println("Alterado: " + municipioPOJO);
			// ----------------------------------------
			municipioPOJO = new Municipio();
			municipioPOJO.setIdMunicipio(1000 + i);
			municipioPOJO = (Municipio) DAO2.consulta(municipioPOJO
					.getIdMunicipio());
			if (municipioPOJO.getIdMunicipio() > 0)
				System.out.println("Consulta: " + municipioPOJO);
			// ----------------------------------------
			if (DAO2.exclui(municipioPOJO))
				System.out.println("Excluído: " + municipioPOJO);
		}
		System.out.println("LISTAGEM DE MUNICIPIOS");
		for (Object p : DAO2.listar(new Criterion[0]))
			System.out.println(p);

		System.out.println("FIM DE ENDERECO");
		DAO2.finalize();
	}

	public static void testaEndereco() throws Throwable {
		GenericWorker<Endereco, Integer> DAO2 = new GenericWorker<Endereco, Integer>(
				Endereco.class);
		System.out.println("INICIO DE ENDEREÇO");
		for (int i = 0; i < QTDE; i++) {
			Endereco registro = new Endereco();
			registro.setIdEndereco(1000 + i);
			registro.setLogradouro("SQN 916 BLOCO A SALA");
			registro.setComplemento("101");
			registro.setBairro("ASA NORTE");
			registro.setCep("70000000");
			// ----- Consulta um Município -----------------
			GenericWorker<Municipio, Integer> DAO = new GenericWorker<Municipio, Integer>(
					Municipio.class);
			Municipio mun = new Municipio();
			mun = (Municipio) DAO.consulta(1);
			registro.setMunicipio(mun);
			DAO.finalize();
			// -----------------------------------------------------
			if (DAO2.inclui(registro))
				System.out.println("Inserido: " + registro);
			// -----------------------------------------------------
			registro.setComplemento("102");
			if (DAO2.altera(registro))
				System.out.println("Alterado: " + registro);
			// -----------------------------------------------------
			registro = (Endereco) DAO2.consulta(registro.getIdEndereco());
			if (registro.getIdEndereco() > 0)
				System.out.println("Consulta: " + registro);
			// -----------------------------------------------------
			if (DAO2.exclui(registro))
				System.out.println("Excluído: " + registro);
		}
		System.out.println("LISTAGEM DE ENDEREÇOS");
		for (Object p : DAO2.listar(new Criterion[0]))
			System.out.println(p);
		System.out.println("FIM DE ENDERECO");
		DAO2.finalize();
	}

	public static void testaPessoa() throws Throwable {
		GenericWorker<Pessoa, Integer> DAO2 = new GenericWorker<Pessoa, Integer>(
				Pessoa.class);
		System.out.println("INICIO DE PESSOA");
		for (int i = 0; i < QTDE; i++) {
			Pessoa registro = new Pessoa();
			registro.setIdPessoa(1000 + i);
			registro.setNome("João da Silva");
			registro.setTelefone("06192459999");
			Calendar calendar = new GregorianCalendar();
			calendar.set(2014, 1, 1);
			registro.setDataNascimento(calendar.getTime());
			registro.setRg("2234498");
			registro.setOrgaoExpedicao("SSPDF");
			registro.setCpf("100" + i);
			// ----- Consulta um Endereco -----------------
			Endereco ender = new Endereco();
			GenericWorker<Endereco, Integer> DAO = new GenericWorker<Endereco, Integer>(
					Endereco.class);
			ender = (Endereco) DAO.consulta(1);
			registro.setEndereco(ender);
			DAO.finalize();
			// -----------------------------------------------------
			if (DAO2.inclui(registro))
				System.out.println("Inserido: " + registro);
			// -----------------------------------------------------
			registro.setTelefone("06192450001");
			if (DAO2.altera(registro))
				System.out.println("Alterado: " + registro);
			// -----------------------------------------------------
			registro = (Pessoa) DAO2.consulta(registro.getIdPessoa());
			if (registro.getIdPessoa() > 0)
				System.out.println("Consulta: " + registro);
			// -----------------------------------------------------
			if (DAO2.exclui(registro))
				System.out.println("Excluído: " + registro);
		}
		System.out.println("LISTAGEM DE PESSOAS");
		for (Object p : DAO2.listar(new Criterion[0]))
			System.out.println(p);
		System.out.println("FIM DE PESSOA");
		DAO2.finalize();
	}

	public static void testaEmpregado() throws Throwable {
		GenericWorker<Empregado, Integer> empHBR = new GenericWorker<Empregado, Integer>(
				Empregado.class);
		System.out.println("INICIO DE EMPREGADO");
		for (int i = 0; i < QTDE; i++) {
			Empregado empregadoPOJO = new Empregado();
			empregadoPOJO.setIdPessoa(3000 + i);
			empregadoPOJO.setNome("Aristóteles Fermino");
			empregadoPOJO.setTelefone("06181273101");
			Calendar calendar = new GregorianCalendar();
			calendar.getTimeInMillis();
			empregadoPOJO.setDataNascimento(calendar.getTime());
			empregadoPOJO.setRg("2369474560");
			empregadoPOJO.setOrgaoExpedicao("SSPGO");
			empregadoPOJO.setCpf("300" + i);
			empregadoPOJO.setCtps("0050023");
			empregadoPOJO.setEmpresaContratante("Catolica");
			calendar.getTimeInMillis();
			;
			empregadoPOJO.setDataContratacao(calendar);
			calendar.getTimeInMillis();
			empregadoPOJO.setDataDemissao(calendar);
			// ----- Seleciona um endereço -----------
			Endereco enderecoDoEmpregado = new Endereco();
			enderecoDoEmpregado.setIdEndereco(1);
			GenericWorker<Endereco, Integer> enderecoHBR = new GenericWorker<Endereco, Integer>(
					Endereco.class);
			enderecoDoEmpregado = (Endereco) enderecoHBR
					.consulta(enderecoDoEmpregado.getIdEndereco());
			empregadoPOJO.setEndereco(enderecoDoEmpregado);
			enderecoHBR.finalize();
			// ------------------------------------------
			if (empHBR.inclui(empregadoPOJO))
				System.out.println("INSERIDO: " + empregadoPOJO);
			// -----------------------------------------------------
			empregadoPOJO.setTelefone("06181273102");
			if (empHBR.altera(empregadoPOJO))
				System.out.println("ALTERADO: " + empregadoPOJO);
			// -----------------------------------------------------
			empregadoPOJO = (Empregado) empHBR.consulta(empregadoPOJO
					.getIdPessoa());
			if (empregadoPOJO.getIdPessoa() > 0)
				System.out.println("CONSULTAR: " + empregadoPOJO);
			// -----------------------------------------------------
			if (empHBR.exclui(empregadoPOJO))
				System.out.println("EXCLUÍDO: " + empregadoPOJO);
		}
		System.out.println("LISTAGEM DE EMPREGADOS");
		for (Empregado empregado : empHBR.listar(new Criterion[0]))
			System.out.println(empregado);
		System.out.println("FIM DE EMPREGADO");
		empHBR.finalize();
	}

	public static void testaConvite() throws Throwable {
		GenericWorker<Convite, ConviteID> con2HBR = new GenericWorker<Convite, ConviteID>(
				Convite.class);
		System.out.println("INICIO DE CONVITE");
		Convite convitePOJO = new Convite();
		ConviteID conviteID = new ConviteID();
		// ----- Consulta Pessoa -----------------
		GenericWorker<Pessoa, Integer> p1HBR = new GenericWorker<Pessoa, Integer>(
				Pessoa.class);
		// ---------------------------------------
		Pessoa p1 = new Pessoa();
		p1.setIdPessoa(new Integer("1"));
		p1 = (Pessoa) p1HBR.consulta(p1.getIdPessoa());
		conviteID.setIdPessoa1(p1.getIdPessoa());
		// ----------------------------------------
		Pessoa p2 = new Pessoa();
		p2.setIdPessoa(new Integer("2"));
		p2 = (Pessoa) p1HBR.consulta(p2.getIdPessoa());
		conviteID.setIdPessoa2(p2.getIdPessoa());
		// ---------------------------------------
		p1HBR.finalize();
		// ---------------------------------------
		Calendar calendar = new GregorianCalendar();
		calendar.set(2014, 9, 11);
		Timestamp tm1 = new Timestamp(calendar.getTimeInMillis());
		tm1.setNanos(0);
		conviteID.setTimestampConvite(tm1);
		// ---------------------------------------
		convitePOJO.setConviteID(conviteID);
		// ---------------------------------------
		Calendar calendar2 = new GregorianCalendar();
		calendar2.set(2014, 9, 11);
		Timestamp tm2 = new Timestamp(calendar2.getTimeInMillis());
		tm2.setNanos(0);
		convitePOJO.setTimestampRecebimento(tm2);
		convitePOJO.setAceiteConvite(false);
		// ---------------------------------------
		if (con2HBR.inclui(convitePOJO))
			System.out.println("Inserido: " + convitePOJO);
		convitePOJO.setAceiteConvite(true);
		// -----------------------------------------------------
		if (con2HBR.altera(convitePOJO))
			System.out.println("Alterado: " + convitePOJO);
		// -----------------------------------------------------
		convitePOJO = (Convite) con2HBR.consulta(convitePOJO.getConviteID());
		if (convitePOJO.getConviteID() != null)
			System.out.println("Consulta: " + convitePOJO);
		// -----------------------------------------------------
		// if (con2HBR.exclui(convitePOJO))System.out.println("Excluído: " +
		// convitePOJO);
		// -----------------------------------------------------
		System.out.println("*****Listando Tudo*******");
		for (Convite p : con2HBR.listar(new Criterion[0]))
			System.out.println(p);
		System.out.println("FIM DE CONVITE");
		con2HBR.finalize();
	}

	public static void testaConversaComMensagem() throws Throwable {
		// ----- Consulta Pessoa 1 ---------------
		Pessoa p1 = new Pessoa();
		GenericWorker<Pessoa, Integer> p1HBR = new GenericWorker<Pessoa, Integer>(
				Pessoa.class);
		p1.setIdPessoa(new Integer("1"));
		p1 = (Pessoa) p1HBR.consulta(p1.getIdPessoa());
		p1HBR.finalize();
		// ----- Consulta Pessoa 2 ---------------
		Pessoa p2 = new Pessoa();
		GenericWorker<Pessoa, Integer> p2HBR = new GenericWorker<Pessoa, Integer>(
				Pessoa.class);
		p2.setIdPessoa(new Integer("2"));
		p2 = (Pessoa) p2HBR.consulta(p2.getIdPessoa());
		p2HBR.finalize();
		// ------ Outros dados da Conversa -------
		Conversa conversa = new Conversa();
		ConversaID conversaPK = new ConversaID();
		conversaPK.setPessoa1(p1);
		conversaPK.setPessoa2(p2);
		Calendar calendar = new GregorianCalendar();
		calendar.set(2014, 8, 5);
		Timestamp tm1 = new Timestamp(calendar.getTimeInMillis());
		tm1.setNanos(0);
		conversaPK.setTimestampConversa(tm1);
		conversa.setConversaPK(conversaPK);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.set(2014, 8, 5);
		Timestamp tm2 = new Timestamp(calendar2.getTimeInMillis());
		tm2.setNanos(0);
		conversa.setTimestampAceite(tm2);
		// ------- TESTE DE CONVERSA --------------
		GenericWorker<Conversa, ConversaID> conversaHBR = new GenericWorker<Conversa, ConversaID>(
				Conversa.class);
		if (conversaHBR.inclui(conversa))
			System.out.println("Inserido: " + conversa);
		conversa = (Conversa) conversaHBR.consulta(conversa.getConversaPK());
		if (conversa.getConversaPK() != null)
			System.out.println("Consulta de Conversa: " + conversa);
		System.out.println("CONVERSAS:");
		for (Conversa p : conversaHBR.listar(new Criterion[0]))
			System.out.println(p);
		System.out.println("FIM DE CONVITE");
		conversaHBR.finalize();
		// ------- Inclusão de Mensagens ---------
		System.out.println("INICIO DE MENSAGENS");
		for (int i = 0; i < 3; i++) {
			Mensagem mensagem = new Mensagem();
			MensagemID mpk = new MensagemID();
			GenericWorker<Mensagem, MensagemID> msgHBR = new GenericWorker<Mensagem, MensagemID>(
					Mensagem.class);
			mpk.setConversa(conversa);
			mpk.setSeqMensagem(new Integer(i));
			mensagem.setMensagemPK(mpk);
			mensagem.setTextoMensagem("Mensagem: " + i);
			msgHBR.inclui(mensagem);
			msgHBR.finalize();
		}
		System.out.println("*****Listando Tudo*******");
		GenericWorker<Mensagem, MensagemID> msgHBR = new GenericWorker<Mensagem, MensagemID>(
				Mensagem.class);
		for (Mensagem p : msgHBR.listar(new Criterion[0]))
			System.out.println(p);
		msgHBR.finalize();
		System.out.println("FIM DE MENSAGENS");
	}

	public static void testaInternauta() {
		Internauta internauta = new Internauta();
		internauta.setIdInternauta(1);
		internauta.setNomeInternauta("Internauta1");
		Acesso acesso = new Acesso();
		// acesso.setInternauta(internauta);
		// acesso.setIdInternauta(1);
		acesso.setEmail("internauta@gmail.com");
		acesso.setSenha("12345678");
		internauta.setAcesso(acesso);
		GenericWorker<Internauta, Integer> DAO1 = new GenericWorker<Internauta, Integer>(
				Internauta.class);
		if (DAO1.inclui(internauta))
			System.out.println("Inserido: " + internauta);
		GenericWorker<Acesso, Integer> DAO2 = new GenericWorker<Acesso, Integer>(
				Acesso.class);
		if (DAO2.inclui(acesso))
			System.out.println("Inserido: " + acesso);
	}

	public static void testaMedicoPaciente() {
		Medico med1 = new Medico();
		med1.setIdMedico(5);
		med1.setNomeMedico("Medico5");
		GenericWorker<Medico, Integer> DAOM1 = new GenericWorker<Medico, Integer>(
				Medico.class);
		if (DAOM1.inclui(med1))
			System.out.println("Inserido: " + med1);
		// ----------------------------------
		Medico med2 = new Medico();
		med2.setIdMedico(6);
		med2.setNomeMedico("Medico6");
		GenericWorker<Medico, Integer> DAOM2 = new GenericWorker<Medico, Integer>(
				Medico.class);
		if (DAOM2.inclui(med2))
			System.out.println("Inserido: " + med2);
		// ----------------------------------
		Paciente pac1 = new Paciente();
		pac1.setIdPaciente(5);
		pac1.setNomePaciente("Paciente5");
		GenericWorker<Paciente, Integer> DAOP1 = new GenericWorker<Paciente, Integer>(
				Paciente.class);
		if (DAOP1.inclui(pac1))
			System.out.println("Inserido: " + pac1);
		// ----------------------------------
		Paciente pac2 = new Paciente();
		pac2.setIdPaciente(6);
		pac2.setNomePaciente("Paciente6");
		GenericWorker<Paciente, Integer> DAOP2 = new GenericWorker<Paciente, Integer>(
				Paciente.class);
		if (DAOP2.inclui(pac2))
			System.out.println("Inserido: " + pac2);
		// ----------------------------------
		// ArrayList<Paciente> atende1 = new ArrayList();
		// atende1.add(pac1);
		// atende1.add(pac2);
		// med1.setPacientes(atende1);
		// med2.setPacientes(atende1);
		ArrayList<Medico> ConsultaMedica1 = new ArrayList<Medico>();
		ConsultaMedica1.add(med1);
		ConsultaMedica1.add(med2);
		pac1.setMedicos(ConsultaMedica1);
		pac2.setMedicos(ConsultaMedica1);
		// Como a classe Paciente é mandatória, pois contém a instrução
		// JoinTable(), então ela governa a inserção e alteração na tabela
		// associativa consulta.
		// Assim o que tem em paciente é o que vai para o banco de dados.
		GenericWorker<Paciente, Integer> DAOP3 = new GenericWorker<Paciente, Integer>(
				Paciente.class);
		if (DAOP3.altera(pac1))
			System.out.println("Inserido: " + pac1);
		GenericWorker<Paciente, Integer> DAOP4 = new GenericWorker<Paciente, Integer>(
				Paciente.class);
		if (DAOP4.altera(pac2))
			System.out.println("Inserido: " + pac2);
	}

	public static void main(String[] args) throws Throwable {
		testaMunicipio();
		testaEndereco();
		testaPessoa();
		testaEmpregado();
		testaConvite();
		testaConversaComMensagem();
	}
}