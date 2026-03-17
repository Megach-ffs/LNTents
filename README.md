# Inventory Management Application

## Product Type
**Camping: Tent**

## Validation Requirements for "Create New Product" Form

The application allows users to create a new product of type "Camping: Tent". The "Add New Product" form includes the following 8 domain-specific fields, along with their validation rules and justification:

### 1. Model Name
*   **Field Type:** Text input
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be between 3 and 50 characters in length.
*   **Argumentation:** The model name is essential for identifying the specific tent. A text input is appropriate for names. The length constraint prevents excessively short or excessively long names.

### 2. Brand
*   **Field Type:** Text input
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be at least 2 characters in length.
*   **Argumentation:** The brand is crucial for the inventory of outdoor equipment. A text input is required since brands vary widely.

### 3. Capacity (Persons)
*   **Field Type:** Text input (Numeric)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be a valid integer.
    *   Must be greater than 0.
*   **Argumentation:** Tent capacity is a standard integer metric (e.g., "2-person tent"). A numeric text input restricts the user to integer values.

### 4. Weight
*   **Field Type:** Text input (Numeric)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be a valid integer greater than 0.
*   **Argumentation:** Weight is a critical specification for tents. An integer input is used here (e.g., in grams or standardized units) to ensure accurate cargo calculations.

### 5. Water Proof Rating
*   **Field Type:** Text input (Numeric)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be a valid integer (e.g., representing Hydrostatic Head in mm).
*   **Argumentation:** Water resistance is a key feature of tents. Representing it as a numeric rating (e.g., 3000mm) provides precise information about the tent's capabilities compared to a simple boolean check.

### 6. Tent Type
*   **Field Type:** Radio buttons
*   **Status:** Required
*   **Validation Rules:**
    *   User must select exactly one option from: "Dome", "Tunnel", "Geodesic".
*   **Argumentation:** Tents are commonly categorized into these structural shapes. Radio buttons are the ideal UI component as they present all options at once and restrict selection to a single valid category.

### 7. Stock
*   **Field Type:** Text input (Numeric)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be an integer >= 0.
*   **Argumentation:** The quantity of items available in the inventory. It cannot be negative.

### 8. Image URL
*   **Field Type:** Text input
*   **Status:** Optional
*   **Validation Rules:**
    *   If provided, must be a formatted string (URL).
*   **Argumentation:** Allows adding a visual reference for the inventory item, which improves usability and item identification.
