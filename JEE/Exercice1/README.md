# Quiz API Documentation

This API allows you to manage and play quizzes, including creating new quizzes, retrieving quizzes by their IDs, playing quizzes, and checking results. You can also delete quizzes if necessary.

## Endpoints

### Résumé des Endpoints

| Méthode | Endpoint                         | Description                                                   |
|---------|----------------------------------|---------------------------------------------------------------|
| **GET**    | `/quizzes`                       | Récupérer la liste de tous les quiz.                          |
| **GET**    | `/quizzes/{id}`                  | Récupérer un quiz spécifique avec toutes ses questions.       |
| **GET**    | `/quizzes/{id}/results`          | Récupérer le score d’un joueur pour un quiz donné.            |
| **POST**   | `/quizzes`                       | Ajouter un nouveau quiz avec ses questions et options.        |
| **POST**   | `/quizzes/{id}/play?name=joueur` | Jouer à un quiz en soumettant des réponses avec un nom donné. |
| **DELETE** | `/quizzes/{id}`                  | Supprimer un quiz et toutes ses questions associées.          |

### 1. **GET /quizzes**

Add a new quiz with its questions and options.

**Response :**
```json
[
  {
    "id": "4aa3812c-aae5-49aa-b381-f81e207dfb0d",
    "questions": [
      {
        "question": "Quelle est la capitale de l'Irlande ?",
        "possibleAnswers": [
          "Londre",
          "Dublin",
          "Tokyo",
          "Minas Tirith"
        ],
        "answer": "Dublin"
      }
    ],
    "scores": {}
  }
]
```

### **2. GET /quizzes/{id}**
   Retrieve a specific quiz by its ID.

**Example Request:**
```bash
GET /quizzes/123e4567-e89b-12d3-a456-426614174000
```
**Response :**
```json
[
  {
    "id": "4aa3812c-aae5-49aa-b381-f81e207dfb0d",
    "questions": [
      {
        "question": "Quelle est la capitale de l'Irlande ?",
        "possibleAnswers": [
          "Londre",
          "Dublin",
          "Tokyo",
          "Minas Tirith"
        ],
        "answer": "Dublin"
      }
    ],
    "scores": {}
  }
]
```

### **3. GET /quizzes/{id}/results**

Retrieve the results of a quiz for a specific player, showing their score.

**Example Request:**

```bash
GET /quizzes/123e4567-e89b-12d3-a456-426614174000/results
```

**Response:**

```json
{
  "PlayerOne": 1
}
```


### 4. **POST /quizzes**

Add a new quiz with its questions and options.

**Request Body:**
```json
{
  "questions": [
    {
      "question": "Quelle est la capitale de l'Irlande ?",
      "possibleAnswers": [
        "Londre",
        "Dublin",
        "Tokyo",
        "Minas Tirith"
      ],
      "answer": "Dublin"
    }
  ]
}
```

### *5. POST /quizzes/{id}/play*

Play a quiz by submitting answers.

**Example Request:**

```bash
POST /quizzes/123e4567-e89b-12d3-a456-426614174000/play?name=PlayerOne
```

**Request Body:**

```json
[
  "Dublin"
]
```
**Response:**

```json
{
  "PlayerOne": 1
}
```

### **6. DELETE /quizzes/{id}**

Delete a quiz and all its associated questions.

**Example Request:**

```bash
DELETE /quizzes/123e4567-e89b-12d3-a456-426614174000
```

**Response:**

```json
le quiz a été supprimé
```