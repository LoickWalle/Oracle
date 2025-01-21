# Exercice 9 : API de gestion des produits avec stock

Créer une API réactive pour gérer un catalogue de produits, en ajoutant des fonctionnalités de gestion des stocks.

## Endpoints

### Résumé des Endpoints

Here is the updated table with your new values:

| Méthode | Endpoint                               | Description                                                      |
|---------|----------------------------------------|------------------------------------------------------------------|
| **GET**    | `/api/products`                       | Retourne tous les produits.                                      |
| **GET**    | `/api/products/{id}`                  | Retourne un produit par ID.                                      |
| **GET**    | `/api/products/search?name=phone`     | Recherche des produits par nom.                                  |
| **POST**   | `/api/products`                       | Crée un nouveau produit.                                         |
| **PUT**    | `/api/products/{id}`                  | Met à jour un produit.                                           |
| **PUT**    | `/api/products/{id}/buy?quantity=5`   | Réduit le stock d’un produit.                                    |
| **DELETE** | `/api/products/{id}`                  | Supprime un produit.                                             |

### 1. *GET /api/products*

Retourne tous les produits.

**Response :**
```json
[
  {
    "id": "b8725d89-c626-462c-adad-b8d6c31e2d87",
    "name": "iPhone 13",
    "price": 799.99,
    "stock": 150
  },
  {
    "id": "3a0292c8-d6bf-4816-8e68-27a4a4d36acf",
    "name": "Galaxy S21",
    "price": 699.99,
    "stock": 200
  },
  {
    "id": "d9a3b133-5818-4a4b-9f5b-53cdc0cb4459",
    "name": "PlayStation 5",
    "price": 499.99,
    "stock": 50
  },
  {
    "id": "7a9f72c6-c643-44e5-aa94-f7f86ab334dd",
    "name": "Air Max 270",
    "price": 149.99,
    "stock": 120
  },
  {
    "id": "6647a83a-060d-47c2-929b-131e63f67dab",
    "name": "Dell Laptop",
    "price": 999.99,
    "stock": 80
  }
]
```

### *2. GET /api/products/{id}*

Retourne un produit par ID.

**Example de requête:**

```bash
GET /api/products/d9a3b133-5818-4a4b-9f5b-53cdc0cb4459
```

**Response :**

```json
{
  "id": "d9a3b133-5818-4a4b-9f5b-53cdc0cb4459",
  "name": "PlayStation 5",
  "price": 499.99,
  "stock": 50
}
```

### *3. GET /api/products/search?name=test*

Recherche des produits par nom.

**Example de requête:**

```bash
GET /api/products/search?name=PlayStation 5
```

**Response:**

```json
{
  "id": "d9a3b133-5818-4a4b-9f5b-53cdc0cb4459",
  "name": "PlayStation 5",
  "price": 499.99,
  "stock": 50
}
```


### 4. *POST /api/products*

Crée un nouveau produit.

**Request Body:**

```json
{
  "name": "PlayStation 6",
  "price": 1499.99,
  "stock": 5
}
```

### *5. PUT /api/products/{id}*

Met à jour un produit.

**Example de requête:**

```bash
PUT /api/products/d9a3b133-5818-4a4b-9f5b-53cdc0cb4459
```

**Request Body:**

```json
{
  "name": "PlayStation 5",
  "price": 299.99,
  "stock": 20
}
```
**Response:**

```json
{
  "id": "d9a3b133-5818-4a4b-9f5b-53cdc0cb4459",
  "name": "PlayStation 5",
  "price": 299.99,
  "stock": 20
}
```

### *6. PUT /api/products/{id}/buy?quantity=5*

Réduit le stock d’un produit.

**Example Request:**

```bash
PUT /api/products/d9a3b133-5818-4a4b-9f5b-53cdc0cb4459/buy?quantity=5
```

**Response:**

```json
{
  "id": "d9a3b133-5818-4a4b-9f5b-53cdc0cb4459",
  "name": "PlayStation 5",
  "price": 299.99,
  "stock": 15
}
```

### *7. DELETE /api/products/{id}*

Supprime un produit.

**Example Request:**

```bash
DELETE /api/products/d9a3b133-5818-4a4b-9f5b-53cdc0cb4459
```

**Response:**

```json
true
```