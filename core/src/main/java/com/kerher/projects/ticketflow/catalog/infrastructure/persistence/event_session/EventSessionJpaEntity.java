package com.kerher.projects.ticketflow.catalog.infrastructure.persistence.event_session;

import com.kerher.projects.ticketflow.catalog.infrastructure.persistence.event.EventJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventSessionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventJpaEntity event;

    @Column(nullable = false)
    private LocalDateTime startsAt;

    @Column(nullable = false)
    private LocalDateTime endsAt;
}
