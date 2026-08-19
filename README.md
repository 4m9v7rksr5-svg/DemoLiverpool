# Android Mobile Developer Technical Test

Tiempo sugerido: 1 hora

## Objetivo

Construir el flujo **PLP (Product List Page) → PDP (Product Detail Page)** consumiendo la
[Fake Store API](https://fakestoreapi.com/docs).

## Qué ya viene resuelto en este proyecto base

Este starter project elimina todo el trabajo mecánico de configuración para que puedas
concentrarte en la parte funcional:

* Configuración de Gradle (AGP, Kotlin, Compose, ViewBinding, Navigation Component).
* Todas las dependencias necesarias ya agregadas y en versiones compatibles entre sí
  (Retrofit, OkHttp, converter Gson, Coroutines, Lifecycle/ViewModel, RecyclerView, Coil,
  Material Components, Navigation Component).
* `FakeStoreApi` con los endpoints `getProducts()` y `getProduct(id)`.
* `ApiClient`, ya apuntando a `https://fakestoreapi.com/`.
* DTOs completos: `ProductResponse` y `RatingResponse`.
* Permiso de `INTERNET` en el `AndroidManifest.xml`.
* `Theme`, colores y strings básicos.
* `MainActivity`, ya alojando el `NavHostFragment`.
* Navigation Component configurado, con el grafo `PLP → PDP` y el argumento `productId`
  ya declarado.
* `ProductListFragment` y `ProductDetailFragment` creados, con su XML base.
* `RecyclerView` agregado al layout del PLP, con `item_product.xml` como Product Card.
* `ComposeView` integrado en el PLP (Compose dentro de una pantalla con Views tradicionales).
* `ProductAdapter` y los `ViewModel` creados como skeleton.
* `ProductRepository` creado como skeleton.
* Placeholders/íconos básicos (back, sort, carrito, imagen de producto).

No necesitas tocar Gradle, agregar dependencias, configurar Retrofit, Navigation ni crear
la estructura del proyecto: todo eso ya está listo.

## Tu implementación comienza aquí

Debes completar la lógica funcional en los siguientes archivos:

* `repository/ProductRepository.kt`
* `ui/plp/ProductListViewModel.kt`
* `ui/plp/ProductListFragment.kt`
* `ui/plp/adapter/ProductAdapter.kt`
* `ui/components/ProductSortCompose.kt`
* `ui/pdp/ProductDetailViewModel.kt`
* `ui/pdp/ProductDetailFragment.kt`

### Qué se espera que implementes

* **`ProductRepository`**: llamada al API a través de `FakeStoreApi`, mapping de
  `ProductResponse` → `Product` (dominio) y manejo básico de errores.
* **`ProductListViewModel`**: carga de productos, exposición de un estado de UI
  (loading / success / empty / error, por ejemplo con `StateFlow`) y aplicación del
  ordenamiento seleccionado.
* **`ProductListFragment`**: observar el estado del ViewModel y reflejarlo en la UI
  (RecyclerView, ProgressBar, estados de error/vacío), y navegar al PDP al seleccionar
  un producto.
* **`ProductAdapter`**: `onBindViewHolder`, binding de datos en la Product Card y carga
  de imagen con Coil.
* **`ProductSortCompose`**: UI de selección de ordenamiento (por ejemplo un
  `ModalBottomSheet`) con las 4 opciones definidas en `SortOption`, e invocar
  `onSortSelected`.
* **`ProductDetailViewModel`**: obtener el `productId` recibido por Navigation Component,
  consultar el detalle del producto y exponer el estado de UI (loading / success / error).
* **`ProductDetailFragment`**: observar el estado del ViewModel y pintar la UI del PDP
  (imagen, título, rating, precio, descripción), además de resolver la navegación de
  `BACK TO PRODUCTS` de vuelta al PLP.

### Ordenamientos requeridos

Definidos en `ui/components/SortOption.kt`:

* Precio: menor a mayor
* Precio: mayor a menor
* Rating: menor a mayor
* Rating: mayor a menor

## Cómo encontrar todo lo pendiente

Todos los puntos que debes resolver están marcados en el código con:

```
// TODO Candidate
```

Puedes buscar ese texto en Android Studio (`Find in Files`, `Cmd/Ctrl + Shift + F`) para
ver la lista completa de tareas.

## Qué se evalúa

* Arquitectura MVVM y manejo de estados.
* Consumo del API desde la arquitectura definida.
* Renderizado de productos en el RecyclerView / Product Cards.
* Ordenamiento funcional.
* Integración de Jetpack Compose dentro de una pantalla con Views tradicionales.
* Navegación PLP → PDP y vuelta.
* Manejo de errores.
* Calidad de código y reutilización.
