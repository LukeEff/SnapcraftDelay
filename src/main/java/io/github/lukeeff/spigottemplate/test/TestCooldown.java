package io.github.lukeeff.spigottemplate.test;

import io.github.lukeeff.spigottemplate.cooldown.Cooldown;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestCooldown {

    private final UUID[] id;
    private final UUID anId;
    private Cooldown cooldown;

    public TestCooldown() {
        id = new UUID[] {
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                UUID.fromString("223e4567-e89b-12d3-a456-426614174000"),
                UUID.fromString("323e4567-e89b-12d3-a456-426614174000"),
                UUID.fromString("423e4567-e89b-12d3-a456-426614174000"),
                UUID.fromString("523e4567-e89b-12d3-a456-426614174000")};

        anId = UUID.fromString("623e4567-e89b-12d3-a456-426614174000");
    }

    @BeforeEach
    public void setUp() {
        this.cooldown = new Cooldown();
        cooldown.registerPlayer(id[0]);
        cooldown.registerPlayer(id[1]);
        cooldown.registerPlayer(id[2]);
    }

    @Test
    public void testRegisterPlayer() {
        final int oldSize = cooldown.getCoolDownHandle().size();
        cooldown.registerPlayer(anId);
        cooldown.registerPlayer(anId);
        assertEquals(cooldown.getCoolDownHandle().size(), oldSize + 1);
    }

    @Test
    public void testGetTimeLeft() {
        cooldown.registerPlayer(anId);
        assertTrue(cooldown.getTimeLeft(anId) > cooldown.getCoolDown() - 300);

    }

    @SneakyThrows
    @Test
    public void testIsOnCoolDown() {
        cooldown.registerPlayer(anId);
        assertTrue(cooldown.isOnCoolDown(anId));
        Thread.sleep(cooldown.getCoolDown());
        assertFalse(cooldown.isOnCoolDown(anId));
    }

    @Test
    public void testRemovePlayer() {
        final int oldSize = cooldown.getCoolDownHandle().size();
        cooldown.registerPlayer(anId);
        cooldown.removePlayer(anId);
        assertEquals(cooldown.getCoolDownHandle().size(), oldSize);
    }

    @AfterEach
    public void tearDown() {

    }

}
