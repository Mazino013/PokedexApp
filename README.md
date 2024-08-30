
<div style="text-align: center;">
  <h1 style="font-size: 36px; margin-bottom: 20px;">Pokedex</h1>
  
  <div style="display: flex; justify-content: center; gap: 20px;">
    <img src="https://github.com/user-attachments/assets/0ea03bb8-8ffd-4ee8-837c-d8278aac7d9d" style="width: 30%; max-width: 300px;">
    <img src="https://github.com/user-attachments/assets/25a12f86-e235-482c-9e38-f24168aafb82" style="width: 30%; max-width: 300px;">
    <img src="https://github.com/user-attachments/assets/f2d7fc15-63ec-4251-8115-dcfc917c50bd" style="width: 30%; max-width: 300px;">
  </div>
</div>


## Features
- **Pokemon-themed App**: A visually appealing application that displays Pokémon in a scrollable list by demonstrating modern Android development with **Jetpack Compose Navigation and Lifecycle**,  Coroutines, Flow, ViewModel, and Material Design based on MVVM architecture.
- **Data Fetching**: Utilizes the [PokeApi](https://pokeapi.co/) to fetch data using Retrofit, ensuring efficient and reliable data retrieval.
- **Type Change Functionality Using  Chips**: Allows users to easily search for Pokemon with a robust search filter feature.
- **Detailed Pokemon Stats**: Clickable items reveal detailed Pokémon stats, including:
  - Pokemon’s sprite
  - Pokemon's name
  - Type
  - number (called order in api response)
    
 ## Architecture

Pokedex is built using the MVVM (Model-View-ViewModel) Architecture, a design pattern that separates the user interface logic from the business logic. This architecture enhances code organization, maintainability, and testability.

### MVVM Components

1. **Model**
   - Represents the data and business logic of the application
   - Handles data retrieval, storage, and manipulation

2. **View**
   - The user interface that displays data from the model
   - Observes and reacts to state changes in the ViewModel

3. **ViewModel**
   - Acts as a bridge between the Model and the View
   - Prepares and manages data for display in the View
   - Handles UI-related tasks such as:
     - Data validation
     - Error handling
   - Provides a clean API for the View to interact with

### Benefits of MVVM in Pokedex

- **Separation of Concerns**: Clear distinction between UI, data, and business logic
- **Testability**: Easier to write unit tests for ViewModels and Models
- **Maintainability**: Changes in one component have minimal impact on others
- **Scalability**: Facilitates easier addition of new features and modifications
