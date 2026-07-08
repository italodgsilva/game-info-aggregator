package io.github.italodgsilva.infra.registry

import io.github.italodgsilva.application.registry.Registry
import io.github.italodgsilva.domain.provider.GameProvider
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Instance

@ApplicationScoped
class CdiGameProviderRegistry(
    providers: Instance<GameProvider>,
) : Registry<GameProvider> {
    private val providers = providers.toList()

    override fun all(): Collection<GameProvider> = providers.toList()
}
