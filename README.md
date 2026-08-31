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
* `ProductListFragment` and `ProductDetailFragment` created, with a minimal placeholder
  XML each (a centered "Hello PLP" / "Hello PDP" text) — you build the actual UI.
* `ProductSortCompose` and `SortOption` created as a starting point for the sort
  control, but not yet hosted anywhere.
* The `ViewModel`s and `ProductRepository` created as skeletons.

You don't need to touch Gradle, add dependencies, configure Retrofit, Navigation, or
create the project structure: all of that is already in place.

## Your implementation starts here

You need to complete the functional logic in the following files:

* `repository/ProductRepository.kt`
* `ui/plp/ProductListViewModel.kt`
* `ui/plp/ProductListFragment.kt` and its layout, `res/layout/fragment_product_list.xml`
* `ui/components/ProductSortCompose.kt`
* `ui/pdp/ProductDetailViewModel.kt`
* `ui/pdp/ProductDetailFragment.kt` and its layout, `res/layout/fragment_product_detail.xml`

### What you're expected to implement

* **`ProductRepository`**: call the API through `FakeStoreApi`, map
  `ProductResponse` → `Product` (domain), and handle basic errors.
* **`ProductListViewModel`**: load products, expose a UI state
  (loading / success / empty / error, e.g. with `StateFlow`), and apply the
  selected sort order.
* **`ProductListFragment`** (and its layout): build the PLP UI — a sort control (host
  `ProductSortCompose` in a `ComposeView`), a `RecyclerView` with a Product Card you
  design, and loading/error/empty states — observe the ViewModel state and reflect it
  in the UI, and navigate to the PDP when a product is selected.
* **`ProductSortCompose`**: sort selection UI (e.g. a `ModalBottomSheet`) with the 4
  options defined in `SortOption`, invoking `onSortSelected`.
* **`ProductDetailViewModel`**: obtain the `productId` received via Navigation
  Component, fetch the product detail, and expose the UI state (loading / success / error).
* **`ProductDetailFragment`** (and its layout): build the PDP UI — image, title,
  rating, price, description, an `ADD TO CART` (dummy) button and a
  `BACK TO PRODUCTS` button — observe the ViewModel state and render it, and resolve
  the `BACK TO PRODUCTS` navigation back to the PLP.

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
