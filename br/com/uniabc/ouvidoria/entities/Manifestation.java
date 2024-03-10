package br.com.uniabc.ouvidoria.entities;
/*
 - Daví Rodrigues Marques
- Edno Brendo Costa e Silva
- Caio Souza Santos
- Kevin de Lima Tenório
- Maria Eduarda Almeida Ramos
- Pedro Lucas da Silva 
   */
public class Manifestation {
	
	public enum ManifestationType {
		  COMPLAINT,
		  SUGGESTION,
		  COMMEND
	}

	public enum Situation {
		  OPENED,
		  IN_PROGRESS,
		  FINISHED
	}
	
	private int code;
	private String title;
	private String description;
	private String date;
	private String author;
	private ManifestationType manifestation;
	private Situation situation;
	
	private static int counter = 1;

	public Manifestation(
			String title, 
			String description, 
			String date, 
			String author, 
			ManifestationType manifestation
		) {
		
		this.title = title;
		this.description = description;
		this.date = date;
		this.author = author;
		this.manifestation = manifestation;
		this.situation = Situation.OPENED;
		this.code = counter++;
	}

	public Manifestation(
			String title, 
			String date,
			ManifestationType manifestation
		) {
		
		this.title = title;
		this.date = date;
		this.description = "Sem descrição";
		this.author = "Anônimo";
		this.manifestation = manifestation;
		this.situation = Situation.OPENED;
		this.code = counter++;
	}

	public int getCode() {
		return code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ManifestationType getManifestationType() {
		return manifestation;
	}

	public void setManifestation(ManifestationType manifestation) {
		this.manifestation = manifestation;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}
}
