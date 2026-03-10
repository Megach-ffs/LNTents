# Inventory Management Application

## Product Type
**Camping: Tent**

## Validation Requirements for "Create New Product" Form

The application allows users to create a new product of type "Camping: Tent". The "Add New Product" form includes the following 6 domain-specific fields, along with their validation rules and justification:

### 1. Model Name
*   **Field Type:** Text input
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be between 3 and 50 characters in length.
*   **Argumentation:** The model name is essential for identifying the specific tent. A text input is appropriate for names. The length constraint prevents excessively short (likely invalid) or excessively long (rendering issues) names.

### 2. Brand
*   **Field Type:** Text input
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be at least 2 characters in length.
*   **Argumentation:** The brand is crucial for the inventory of outdoor equipment. A text input is required since brands vary widely. A minimum length of 2 characters ensures valid brand names (e.g., "MSR", "REI", "OEX") are permitted while filtering out single-letter typos.

### 3. Capacity (Persons)
*   **Field Type:** Number input (or Slider)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be an integer value.
    *   Must be greater than 0 and less than or equal to 20.
*   **Argumentation:** Tent capacity is a standard integer metric (e.g., "2-person tent"). A number input explicitly restricts the user to numeric values. The range ensures reasonable capacity values, as a 0-person tent is invalid and a tent larger than 20-person capacity is highly unlikely for typical inventory.

### 4. Weight (in kg)
*   **Field Type:** Number input (Decimal)
*   **Status:** Required
*   **Validation Rules:**
    *   Cannot be empty.
    *   Must be a positive decimal number greater than 0.0.
    *   Cannot exceed 100.0 kg.
*   **Argumentation:** Weight is a critical specification for tents, particularly for backpacking. A decimal number input is necessary because tent weights often include fractions (e.g., 2.5 kg). It must be greater than zero.

### 5. Season Rating
*   **Field Type:** Drop-down menu
*   **Status:** Required
*   **Validation Rules:**
    *   User must select exactly one option from the predefined list: "1-Season", "2-Season", "3-Season", "4-Season", "5-Season/Expedition".
    *   Cannot be empty/unselected.
*   **Argumentation:** Tents follow standard season classifications. A drop-down menu is the perfect UI component because it restricts the user to valid, predefined categories, preventing typos and unstructured data entry.

### 6. Is Waterproof
*   **Field Type:** Check box (or Switch)
*   **Status:** Optional (though by default, it acts as a boolean state)
*   **Validation Rules:**
    *   Only accepts true/false boolean state based on whether it is checked or not.
*   **Argumentation:** Water resistance is a key feature of tents. A check box is the most intuitive and standard way to represent a binary (Yes/No) property.

*(Note: The stock quantity is handled independently as part of the assignment requirements and is validated to be $\ge$ 0).*
