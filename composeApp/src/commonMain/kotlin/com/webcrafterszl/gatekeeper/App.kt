package com.webcrafterszl.gatekeeper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.webcrafterszl.gatekeeper.navigation.AppNavigation
import com.webcrafterszl.gatekeeper.navigation.AppRoute
import com.webcrafterszl.gatekeeper.ui.auth.LoginScreen
import com.webcrafterszl.gatekeeper.ui.auth.RegisterScreen
import com.webcrafterszl.gatekeeper.ui.auth.SelectionScreen
import com.webcrafterszl.gatekeeper.ui.components.AppButton
import com.webcrafterszl.gatekeeper.ui.screens.CredencialCrudScreen
import com.webcrafterszl.gatekeeper.ui.screens.PortadorCrudScreen
import com.webcrafterszl.gatekeeper.ui.screens.ReservaCrudScreen
import com.webcrafterszl.gatekeeper.ui.screens.VisitanteCrudScreen
import com.webcrafterszl.gatekeeper.viewmodel.CredencialViewModel
import com.webcrafterszl.gatekeeper.viewmodel.PortadorViewModel
import com.webcrafterszl.gatekeeper.viewmodel.ReservaViewModel
import com.webcrafterszl.gatekeeper.viewmodel.VisitanteViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navigation = remember { AppNavigation() }
        val portadorViewModel = remember { PortadorViewModel() }
        val credencialViewModel = remember { CredencialViewModel() }
        val visitanteViewModel = remember { VisitanteViewModel() }
        val reservaViewModel = remember { ReservaViewModel() }

        when (navigation.currentRoute) {
            AppRoute.Login -> LoginScreen(
                onLoginClick = { navigation.navigateTo(AppRoute.Selection) },
                onRegisterClick = { navigation.navigateTo(AppRoute.Register) },
            )

            AppRoute.Register -> RegisterScreen(
                onRegisterClick = { navigation.navigateTo(AppRoute.Login) },
                onBackClick = { navigation.navigateTo(AppRoute.Login) },
            )

            AppRoute.Selection -> SelectionScreen(
                onAdminClick = { navigation.navigateTo(AppRoute.AdminMenu) },
                onUserClick = { navigation.navigateTo(AppRoute.UserMenu) },
                onLogoutClick = { navigation.navigateTo(AppRoute.Login) },
            )


            AppRoute.AdminMenu -> MenuScreen(
                title = "Menu Administrativo",
                onPrimary = { navigation.navigateTo(AppRoute.PortadorCrud) },
                onSecondary = { navigation.navigateTo(AppRoute.CredencialCrud) },
                primaryLabel = "Portador",
                secondaryLabel = "Credencial RFID",
                onBack = { navigation.navigateTo(AppRoute.Selection) },
            )

            AppRoute.UserMenu -> MenuScreen(
                title = "Menu Autoatendimento",
                onPrimary = { navigation.navigateTo(AppRoute.VisitanteCrud) },
                onSecondary = { navigation.navigateTo(AppRoute.ReservaCrud) },
                primaryLabel = "Visitante",
                secondaryLabel = "Reserva",
                onBack = { navigation.navigateTo(AppRoute.Selection) },
            )

            AppRoute.PortadorCrud -> PortadorCrudScreen(
                viewModel = portadorViewModel,
                onBack = { navigation.navigateTo(AppRoute.AdminMenu) },
            )

            AppRoute.CredencialCrud -> CredencialCrudScreen(
                viewModel = credencialViewModel,
                onBack = { navigation.navigateTo(AppRoute.AdminMenu) },
            )

            AppRoute.VisitanteCrud -> VisitanteCrudScreen(
                viewModel = visitanteViewModel,
                onBack = { navigation.navigateTo(AppRoute.UserMenu) },
            )

            AppRoute.ReservaCrud -> ReservaCrudScreen(
                viewModel = reservaViewModel,
                onBack = { navigation.navigateTo(AppRoute.UserMenu) },
            )
        }
    }
}

@Composable
private fun MenuScreen(
    title: String,
    primaryLabel: String,
    secondaryLabel: String,
    onPrimary: () -> Unit,
    onSecondary: () -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(title, style = MaterialTheme.typography.headlineMedium)
        AppButton(text = primaryLabel, modifier = Modifier.padding(top = 24.dp), onClick = onPrimary)
        AppButton(text = secondaryLabel, modifier = Modifier.padding(top = 12.dp), onClick = onSecondary)
        AppButton(text = "Voltar", modifier = Modifier.padding(top = 24.dp), onClick = onBack)
    }
}