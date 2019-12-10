# instant_system
	Généralités sur l'API de gestion Parking
L'url de l'api que j’ai utilisé pour pouvoir recupérer les données des parkings est la suivante:  https://data.rennesmetropole.fr/explore/dataset/export-api-parking-citedia/api/. 
L'API de gestion des parkings est une API REST, permet d'envoyer des requêtes de différents types :
	GET pour lire les ressources
	POST pour valider une ressource sans la créer
	
Description des requêtes GET
Ces requêtes permettent de lire un ou n objets de la source de données.
    Remarque préalable:
	devant chaque requête, il faut ajouter l'url du serveur. Dans les exemples, http://localhost:8080/api
	
GET de collections d'objets
/parking : renvoie la liste complète des objets parkings de la source de données http://localhost:8080/api/parking

GET d'un objet
/parking/{id} : renvoie l'objet parking ayant l'identifiant {id} qui correponds à l’object fields.id dans la source de données
http://localhost:8080/api/parking/310

Description des requêtes POST
/parking: valide un objet parking sans le créer. Le corps de la requête doit contenir les différents champs de l'objet à créer en json

Exemple :
http://localhost:8080/api/parking
corps en json :
{
"fields": {
		"id": "12",
		"status": "OUVERT",
		"orgahoraires": "24h/24 et 7j/7. Bureau ouvert, 7h30 à 20h30 sauf dimanche et jours fériés.",
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
Le champ suivant est obligatoire lors d'un POST :
id: id parking
 

Stack technique

1- Utilisation du ModelMapper afin de mapper l’objet Parking reçu par l’API vers l’objet ParkingDto qui sert à alimenter les applications mobiles.
j’aurai pu envoyer directement l’objet servi par l’api au applications mobiles mais ce n’est pas une bonne façon de faire, dû au fait qu'il peut y avoir un changement de fournisseur de données ou un ajout d’une nouvelle source de données, ce qui va impliquer des changement majeurs coté application mobiles.Donc le choix d’un objet commun entre le serveur et le client reste la solution la plus pertinente.

2- Utilisation RestController de Spring Boot qui n’est pas une implémentation de JAX-RS mais une alternative. Par contre afin de respecter la spécification JAX-RS il faut implémenter Jersey et ne pas utiliser RestController.
3- Gestion des exception de l’api, le procédé que j’ai pu suivre pour gérer les exceptions et de créer une méthode « exception handler » spécifique à chaque type d'exception que je souhaite capturer, puis une dernière méthode plus générique pour gérer les exceptions qui ne correspondent à aucun cas. Pour que ces méthodes soient globales à l'application, j’ai englobé ces méthodes dans un Controller Advice.

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

3- Utilisation de l’interface Validator de Spring permettant de valider la structure d’un objet, l’interface fournit un objet Error qui permet de récupérer l’erreur en cours de validation. Dans le cas de ce test j’ai mis un validator juste pour contrôler le champ id lors de l’envoi de la requête Post.

@Component("beforeCreateParkingValidator")
public class ParkingValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ParkingDto.class.equals(clazz);
    }
     @Override
    public void validate(Object obj, Errors errors) {
    	ParkingDto parkingDto = (ParkingDto) obj;
        if (checkInputString(parkingDto.getFields().getId())) {
            errors.rejectValue("id", "id.empty");
        }
    }
    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
 
4- Pour les améliorations dans le cadre d’un vrai projet, il faut rajouter ces points suivants :
	Création de fichier de Properties pour chaque environnement.
	Ajout de Spring Security afin de sécuriser l’application.
	Développement de Test Unitaire.
	Ajout de Swagger pour la documentation de l’API.
 
