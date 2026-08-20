# Android Mobile Developer Technical Test

Suggested time: 1 hour

## Goal

Build the **PLP (Product List Page) → PDP (Product Detail Page)** flow consuming the
[Fake Store API](https://fakestoreapi.com/docs).

## What this base project already solves

This starter project removes all the mechanical setup work so you can focus on the
functional part:

* Gradle configuration (AGP, Kotlin, Compose, ViewBinding, Navigation Component).
* All required dependencies already added and in compatible versions with each other
  (Retrofit, OkHttp, Gson converter, Coroutines, Lifecycle/ViewModel, RecyclerView, Coil,
  Material Components, Navigation Component).
* `FakeStoreApi` with the `getProducts()` and `getProduct(id)` endpoints.
* `ApiClient`, already pointing to `https://fakestoreapi.com/`.
* Complete DTOs: `ProductResponse` and `RatingResponse`.
* `INTERNET` permission in `AndroidManifest.xml`.
* Base `Theme`, colors and strings.
* `MainActivity`, already hosting the `NavHostFragment`.
* Navigation Component configured, with the `PLP → PDP` graph and the `productId`
  argument already declared.
* `ProductListFragment` and `ProductDetailFragment` created, with their base XML.
* `RecyclerView` added to the PLP layout, with `item_product.xml` as the Product Card.
* `ComposeView` integrated into the PLP (Compose inside a traditional Views screen).
* `ProductAdapter` and the `ViewModel`s created as skeletons.
* `ProductRepository` created as a skeleton.
* Basic placeholders/icons (back, sort, cart, product image).

You don't need to touch Gradle, add dependencies, configure Retrofit, Navigation, or
create the project structure: all of that is already in place.

## Your implementation starts here

You need to complete the functional logic in the following files:

* `repository/ProductRepository.kt`
* `ui/plp/ProductListViewModel.kt`
* `ui/plp/ProductListFragment.kt`
* `ui/plp/adapter/ProductAdapter.kt`
* `ui/components/ProductSortCompose.kt`
* `ui/pdp/ProductDetailViewModel.kt`
* `ui/pdp/ProductDetailFragment.kt`

### What you're expected to implement

* **`ProductRepository`**: call the API through `FakeStoreApi`, map
  `ProductResponse` → `Product` (domain), and handle basic errors.
* **`ProductListViewModel`**: load products, expose a UI state
  (loading / success / empty / error, e.g. with `StateFlow`), and apply the
  selected sort order.
* **`ProductListFragment`**: observe the ViewModel state and reflect it in the UI
  (RecyclerView, ProgressBar, error/empty states), and navigate to the PDP when a
  product is selected.
* **`ProductAdapter`**: `onBindViewHolder`, binding the data into the Product Card, and
  loading the image with Coil.
* **`ProductSortCompose`**: sort selection UI (e.g. a `ModalBottomSheet`) with the 4
  options defined in `SortOption`, invoking `onSortSelected`.
* **`ProductDetailViewModel`**: obtain the `productId` received via Navigation
  Component, fetch the product detail, and expose the UI state (loading / success / error).
* **`ProductDetailFragment`**: observe the ViewModel state and render the PDP UI
  (image, title, rating, price, description), plus resolve the `BACK TO PRODUCTS`
  navigation back to the PLP.

### Required sort options

Defined in `ui/components/SortOption.kt`:

* Price: low to high
* Price: high to low
* Rating: low to high
* Rating: high to low

## How to find everything that's pending

Every point you need to solve is marked in the code with:

```
// TODO Candidate
```

You can search for that text in Android Studio (`Find in Files`, `Cmd/Ctrl + Shift + F`)
to see the full list of tasks.

## What's evaluated

* MVVM architecture and state management.
* API consumption from the defined architecture.
* Rendering products in the RecyclerView / Product Cards.
* Functional sorting.
* Jetpack Compose integration inside a traditional Views screen.
* PLP → PDP navigation and back.
* Error handling.
* Code quality and reusability.
