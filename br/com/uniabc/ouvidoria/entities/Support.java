package br.com.uniabc.ouvidoria.entities;

import java.util.ArrayList;

import br.com.uniabc.ouvidoria.entities.Manifestation.ManifestationType;

public class Support {
	private ArrayList<Manifestation> manifestations = new ArrayList<Manifestation>();

	public static boolean isValid(String text) {
		boolean validation = false;
		if (text != null && !(text.isEmpty())) {
			validation = true;
		}//Verificação de texto 

		return validation;
	}

	public String addManifestation(Manifestation newManifestation) {
		if (newManifestation != null) {
			manifestations.add(newManifestation);
		}// se for diferente de null ele adicona

		return "A manifestação de número " + newManifestation.getCode() + " foi adicionada!";
	}

	public String listManifestation() {
		String manifestationList = "A lista está vazia";

		if (!manifestations.isEmpty()){
			manifestationList = "Lista de Manifestações\n"
					+ "===========================================\n";
			for (Manifestation item : manifestations) {
				manifestationList += String.format(
						"%s - ", item.getCode())
						+ "Titulo: " + item.getTitle()
						+ "\nData: " + item.getDate() + "\n"
						+ "===========================================\n";
			}
		}

		return manifestationList;
	}

	public String listComplaint() {
		String complaintList = "A lista está vazia";

		if (!manifestations.isEmpty()){
			complaintList = "Lista de Reclamações\n"
					+ "===========================================\n";
			for (Manifestation item : manifestations) {
				if(item.getManifestationType() == ManifestationType.COMPLAINT) {
					complaintList += String.format(
							"%s - ", item.getCode())
							+ "Titulo: " + item.getTitle()
							+ "\nData: " + item.getDate() + "\n"
							+ "===========================================\n";
				}

			}
		}

		return complaintList;
	}
	
	public String listCommend() {
		String commendList = "A lista está vazia";

		if (!manifestations.isEmpty()) {
			commendList = "Lista de Elogio\n"
					+ "===========================================\n";
			for (Manifestation item : manifestations) {
				if(item.getManifestationType() == ManifestationType.COMMEND) {
					commendList += String.format(
							"%s - ", item.getCode())
							+ "Titulo: " + item.getTitle()
							+ "\nData: " + item.getDate() + "\n"
							+ "===========================================\n";
				}
			}
		}

		return commendList;
	}
	
	public String listSuggestion() {
		String suggestionList = "A lista está vazia";

		if (!manifestations.isEmpty()) {
			suggestionList = "Lista de Sugestão\n"
					+ "===========================================\n";
			for (Manifestation item : manifestations) {
				if(item.getManifestationType() == ManifestationType.SUGGESTION) {
					suggestionList += String.format(
							"%s - ", item.getCode())
							+ "Titulo: " + item.getTitle()
							+ "\nData: " + item.getDate() + "\n"
							+ "===========================================\n";
				}
			}
		}

		return suggestionList;// pesquisar por id
	}

	public void updateManifestationById(int code, String newTitle) {
		Manifestation searchManifestation = getManifestationById(code);
		searchManifestation.setTitle(newTitle);
	}

	public void removeManifestationById(int code) {
		Manifestation searchManifestation = getManifestationById(code);
		manifestations.remove(searchManifestation);
	}

	public Manifestation getManifestationById(int manifestationCode) {
		Manifestation manifestationObj = null;
		for (Manifestation item : manifestations) {
			if (item.getCode() == manifestationCode) {
				manifestationObj = item;
				break;
			}
		}

		return manifestationObj;
	}

	public void changeManifestationStatus(int code, Manifestation.Situation newStatus){
		Manifestation searchManifestation = getManifestationById(code);
		searchManifestation.setSituation(newStatus);// serve para infomar o novo status 
	}
}
