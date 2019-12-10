# instant_system
G�n�ralit�s sur l'API de gestion Parking
L'url de l'api que j�ai utilis� pour pouvoir recup�rer les donn�es des parkings est la suivante: �https://data.rennesmetropole.fr/explore/dataset/export-api-parking-citedia/api/.�
L'API de gestion des parkings est une API REST, permet d'envoyer des requ�tes de diff�rents types :
* GET pour lire les ressources
* POST pour valider une ressource sans la cr�er
Description des requ�tes GET
Ces requ�tes permettent de lire un ou n objets de la source de donn�es.
Remarque pr�alable:
* devant chaque requ�te, il faut ajouter l'url du serveur. Dans les exemples, http://localhost:8080/api
GET de collections d'objets
/parking : renvoie la liste compl�te des objets parkings de la source de donn�es http://localhost:8080/api/parking
GET d'un objet
/parking/{id} : renvoie l'objet parking ayant l'identifiant {id} qui correponds � l�object fields.id dans la source de donn�es
http://localhost:8080/api/parking/310
Description des requ�tes POST
/parking: valide un objet parking sans le cr�er. Le corps de la requ�te doit contenir les diff�rents champs de l'objet � cr�er en json

Exemple :
http://localhost:8080/api/parking
corps en json :
{
"fields": {
		"id": "12",
		"status": "OUVERT",
		"orgahoraires": "24h/24 et 7j/7. Bureau ouvert, 7h30 � 20h30 sauf dimanche et jours f�ri�s.",
		"tarif_15": "0.40",
		"tarif_1h30": "2.20",
		"tarif_30": "0.80",
		"tarif_1h": "1.60",
		"tarif_3h": "4.00",
		"tarif_2h": "2.80",
		"tarif_4h": "5.20",
		"max": 1089,
		"free": 266
	},
	"geometry": {
		"type": "Point",
		"coordinates": [-1.678624547, 48.10476252]
	}
}
Le champ suivant est obligatoire lors des POST :
* id: id parking
�


Stack technique
1- Utilisation du ModelMapper afin de mapper l�objet Parking re�u par l�API vers l�objet ParkingDto qui sert � alimenter les applications mobiles.
j�aurai pu envoyer directement l�objet servi par l�api au applications mobiles mais ce n�est pas une bonne fa�on de faire, d� au fait qu'il peut y avoir un changement de fournisseur de donn�es ou un ajout d�une nouvelle source de donn�es, ce qui va impliquer des changement majeurs cot� application mobiles.Donc le choix d�un objet commun entre le serveur et le client reste la solution la plus pertinente.
2- Utilisation RestController de Spring Boot qui n�est pas une impl�mentation de JAX-RS mais une alternative. Par contre afin de respecter la sp�cification JAX-RS il faut impl�menter Jersey et ne pas utiliser RestController.
3- Gestion des exception de l�api, le proc�d� que j�ai pu suivre pour g�rer les exceptions et de cr�er une m�thode � exception handler � sp�cifique � chaque type d'exception que je souhaite capturer, puis une derni�re m�thode plus g�n�rique pour g�rer les exceptions qui ne correspondent � aucun cas. Pour que ces m�thodes soient globales � l'application, j�ai englob� ces m�thodes dans un Controller Advice.

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleException(ResourceNotFoundException ex, WebRequest request) throws Exception {
		GenericExceptionResponseDto nevEx = new GenericExceptionResponseDto(ex.getMessage(),
				HttpStatus.NOT_FOUND.value(), request.getDescription(false));

		return new ResponseEntity<Object>(nevEx, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {
		GenericExceptionResponseDto nevEx = new GenericExceptionResponseDto(ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));

		return new ResponseEntity<Object>(nevEx, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

3- Utilisation de l�interface Validator de Spring permettant de valider la structure d�un objet, l�interface fournit un objet Error qui permet de r�cup�rer l�erreur en cours de validation. Dans le cas de ce test j�ai mis un validator juste pour contr�ler le champ id lors de l�envoi de la requ�te Post.

@Component("beforeCreateParkingValidator")
public class ParkingValidator implements Validator {
����@Override
����public boolean supports(Class<?> clazz) {
��������return ParkingDto.class.equals(clazz);
����}
�����@Override
����public void validate(Object obj, Errors errors) {
����	ParkingDto parkingDto = (ParkingDto) obj;
��������if (checkInputString(parkingDto.getFields().getId())) {
������������errors.rejectValue("id", "id.empty");
��������}
����}
����private boolean checkInputString(String input) {
��������return (input == null || input.trim().length() == 0);
����}
}
�
4- Pour les am�liorations dans le cadre d�un vrai projet, il faut rajouter ces points suivants :
* Cr�ation de fichier de Properties pour chaque environnement
* Ajout de Spring Security afin de s�curiser l�application�
* D�veloppement de Test Unitaire
* Ajout de Swagger pour la documentation de l�API
