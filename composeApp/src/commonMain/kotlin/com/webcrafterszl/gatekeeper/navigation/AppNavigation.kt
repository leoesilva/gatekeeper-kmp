package com.webcrafterszl.gatekeeper.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed interface AppRoute {
	data object Login : AppRoute
	data object Register : AppRoute
	data object Selection : AppRoute
	data object FirebaseConnectionTest : AppRoute
	data object AdminMenu : AppRoute
	data object UserMenu : AppRoute
	data object PortadorCrud : AppRoute
	data object CredencialCrud : AppRoute
	data object VisitanteCrud : AppRoute
	data object ReservaCrud : AppRoute
}

class AppNavigation(initialRoute: AppRoute = AppRoute.Login) {
	var currentRoute by mutableStateOf<AppRoute>(initialRoute)
		private set

	fun navigateTo(route: AppRoute) {
		currentRoute = route
	}

	fun goBackToSelection() {
		currentRoute = AppRoute.Selection
	}
}