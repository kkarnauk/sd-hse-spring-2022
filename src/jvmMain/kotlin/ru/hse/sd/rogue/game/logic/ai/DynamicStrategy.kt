package ru.hse.sd.rogue.game.logic.ai

import ru.hse.sd.rogue.game.logic.position.Direction
import java.util.*

/**
 * Strategy with the behaviour based on set of rules, which are invoked each [nextMovement]
 */
class DynamicStrategy(private val defaultStrategy: MobStrategy) : MobStrategy() {
    private class StrategyRule(
        val priority: Int,
        val orderOfAdd: Int,
        val mobStrategy: MobStrategy,
        val rule: () -> Boolean
    ) : Comparable<StrategyRule> {
        override fun compareTo(other: StrategyRule): Int {
            return if (priority == other.priority) {
                orderOfAdd.compareTo(other.orderOfAdd)
            } else {
                priority.compareTo(other.priority)
            }
        }
    }

    private val strategyRules = TreeSet<StrategyRule>()

    /**
     * Add [rule], which will be invoked each tick.
     *
     * The strategy for each tick is the strategy whose [rule] return true with the lowest priority.
     * Among the strategies with the equal priority the earliest added will be activated
     *
     * If no rule returned True, then [defaultStrategy] will be activated
     */
    fun withStrategyRule(strategy: MobStrategy, priority: Int = 1, rule: () -> Boolean): DynamicStrategy {
        strategyRules.add(StrategyRule(priority, strategyRules.size, strategy, rule))
        return this
    }

    override fun nextMovement(): Direction? {
        return strategyRules.firstOrNull { it.rule() }.let { it?.mobStrategy ?: defaultStrategy }.nextMovement()
    }
}