package br.com.uniabc.ouvidoria.main;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import br.com.uniabc.ouvidoria.entities.Manifestation;
import br.com.uniabc.ouvidoria.entities.Manifestation.ManifestationType;
import br.com.uniabc.ouvidoria.entities.Support;

public class Main {
	
	static void showAddManifestationWindow(Support supportObj) {
		String title = JOptionPane.showInputDialog("Digite o título da manifestação: ");
		String description = JOptionPane.showInputDialog("Digite a descrição da manifestação: ");
		String author = JOptionPane.showInputDialog("Autor da manifestação: ");
		String date = "" + LocalDate.now();
		
		Manifestation.ManifestationType manifestationTypeEnum = null;
		
		String manifestationType = "Option";
		
		while (manifestationType != null) {
			manifestationType = JOptionPane.showInputDialog(
					"Tipos de manifestação:\n"
					+ "S - Sugestão\n"
					+ "R - Reclamação\n"
					+ "E - Elogio\n"
					+ "\nDigite o tipo da manifestação:"
					);

			if (Support.isValid(manifestationType)) {
				if (manifestationType.equalsIgnoreCase("S")) {
					manifestationTypeEnum = ManifestationType.SUGGESTION;
					break;
				} else if (manifestationType.equalsIgnoreCase("R")) {
					manifestationTypeEnum = ManifestationType.COMPLAINT;
					break;
				} else if (manifestationType.equalsIgnoreCase("E")) {
					manifestationTypeEnum = ManifestationType.COMMEND;
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Você precisa atribuir um tipo para a manifestação!"
				);
			}
		}
		String successMessage = null;
		if (Support.isValid(title) && Support.isValid(description) && Support.isValid(author)) {
			successMessage = supportObj.addManifestation(new Manifestation(
					title, 
					description, 
					date, 
					author, 
					manifestationTypeEnum
				));
		}
		else {
			successMessage = supportObj.addManifestation(new Manifestation(
					title, 
					date,
					manifestationTypeEnum
				));
		}

		JOptionPane.showMessageDialog(null, successMessage);
	}

	static void showRemoveManifestationWindow(Support supportObj) {//verificação de numeros
		try {
			JOptionPane.showMessageDialog(null, supportObj.listManifestation());
			int code = Integer.parseInt(
					JOptionPane.showInputDialog("Digite o código da manifestação que deseja remover: ")
			);

			supportObj.removeManifestationById(code);
		}
		catch (NumberFormatException error){
			JOptionPane.showMessageDialog(null,
					"Digite apenas números!\n"
					+ error
			);
		}
		catch (Exception error){
			JOptionPane.showMessageDialog(null,
					"""
                      Ops... Ocorreu um erro! Entre em contato com o desenvolvedor através do email:\n
                      dev.desenvolvedor@uniabc.com.br \n
                   """ + error);
		}
	}

	static void showChangeStatusWindow(Support supportObj) {
		JOptionPane.showMessageDialog(null, supportObj.listManifestation());
		String codeStr = JOptionPane.showInputDialog(
				"Digite o código da  manifestação que deseja alterar o status : "
		);
		int code = Integer.parseInt(codeStr);

		String manifestationStatus = "Option";

		Manifestation.Situation situation =  null;

		while (manifestationStatus != null) {
			manifestationStatus = JOptionPane.showInputDialog(
					"Opções de Status:\n"
							+ "A - Em aberto\n"
							+ "B - Em andamento\n"
							+ "C - Finalizado\n"
							+ "\nDigite o novo status da manifestação:"
			);

			if (manifestationStatus.equalsIgnoreCase("A")) {
				situation = Manifestation.Situation.OPENED;
				break;
			} else if (manifestationStatus.equalsIgnoreCase("B")) {
				situation = Manifestation.Situation.IN_PROGRESS;
				break;
			} else if (manifestationStatus.equalsIgnoreCase("C")) {
				situation = Manifestation.Situation.FINISHED;
				break;
			} else {
				JOptionPane.showMessageDialog(null, "Você digitou uma opção inválida!");
			}
		}
		supportObj.changeManifestationStatus(code, situation);
	}

	static void showUpdateManifestationWindow(Support supportObj) {
		try {
			JOptionPane.showMessageDialog(null, supportObj.listManifestation());
			
			String codeStr = JOptionPane.showInputDialog("Digite o código da manifestação que deseja alterar :");
			int code = Integer.parseInt(codeStr);
			String newTitle = JOptionPane.showInputDialog("Digite o novo titulo da manifestação : ");
			
			if (Support.isValid(newTitle)) {
				supportObj.updateManifestationById(code, newTitle);
				JOptionPane.showMessageDialog(null, "Titulo alterado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Titulo inválido!");
			}
		} catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(null, "Este campo só aceita números!\n" + error);
		}
	}

	static void showManifestationByIdWindow(Support supportObj){
		try {
			JOptionPane.showMessageDialog(null, supportObj.listManifestation());
			
			String codeStr = JOptionPane.showInputDialog("Digite o código da manifestação");
			int code = Integer.parseInt(codeStr);

			Manifestation searchManifestation = supportObj.getManifestationById(code);

			String manifestationType;
			if (searchManifestation.getManifestationType().equals(ManifestationType.SUGGESTION)) {
				manifestationType = "Sugestão";
			} else if (searchManifestation.getManifestationType().equals(ManifestationType.COMPLAINT)) {
				manifestationType = "Reclamação";
			} else if (searchManifestation.getManifestationType().equals(ManifestationType.COMMEND)) {
				manifestationType = "Elogio";
			} else {
				manifestationType = "Desconhecido";
			}
			
			String manifestationStatus;
			if (searchManifestation.getSituation().equals(Manifestation.Situation.OPENED)) {
				manifestationStatus = "Em aberto";
			} else if (searchManifestation.getSituation().equals(Manifestation.Situation.IN_PROGRESS)) {
				manifestationStatus = "Em andamento";
			} else if (searchManifestation.getSituation().equals(Manifestation.Situation.FINISHED)) {
				manifestationStatus = "Finalizado";
			} else {
				manifestationStatus = "Desconhecido";
			}

			JOptionPane.showMessageDialog(null,
					"Código: " + searchManifestation.getCode()
							+ "\nTitulo: " + searchManifestation.getTitle()
							+ "\nDescrição: " + searchManifestation.getDescription()
							+ "\nTipo: " + manifestationType
							+ "\nAutor: " + searchManifestation.getAuthor()
							+ "\nStatus: " + manifestationStatus
							+ "\nData de criação: " + searchManifestation.getDate()
			);
		} catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(null,
					"Este campo só aceita números! Tente novamente"
			);
		}
	}

	public static void main(String[] args) {
		Support supportObj = new Support();
		
		int option = 0;

		do {

			try{
				option = Integer
						.parseInt(JOptionPane.showInputDialog(
								"1 - Adicionar nova manifestação\n"
								+ "2 - Listar manifestações\n"
								+ "3 - Listar sugestões\n"
								+ "4 - Listar elogios\n"
								+ "5 - Listar reclamações\n"
								+ "6 - Remover manifestação por codigo\n"
								+ "7 - Editar manifestação por codigo\n"
								+ "8 - Mostrar manifestação por codigo\n"
								+ "9 - Alterar status da manifestação\n"
								+ "10 - Sair\n"
								+ "Digite uma opção: "
						));

				if (option == 1) {
					showAddManifestationWindow(supportObj);
				} else if (option == 2) {
					JOptionPane.showMessageDialog(null, supportObj.listManifestation());
				} else if (option == 3) {
					JOptionPane.showMessageDialog(null, supportObj.listSuggestion());
				} else if (option == 4) {
					JOptionPane.showMessageDialog(null, supportObj.listCommend());
				} else if (option == 5) {
					JOptionPane.showMessageDialog(null, supportObj.listComplaint());
				} else if (option == 6) {
					showRemoveManifestationWindow(supportObj);
				} else if (option == 7) {
					showUpdateManifestationWindow(supportObj);
				} else if (option == 8) {
					showManifestationByIdWindow(supportObj);
				} else if (option == 9) {
					showChangeStatusWindow(supportObj);
				} else if (option != 10) {
					JOptionPane.showMessageDialog(null, "Opção inválida!");
				}
			} catch (NumberFormatException error) {
				JOptionPane.showMessageDialog(null, "Erro: Este campo só aceita números!\n" + error);
			}

		} while (option != 10);
	}
}

