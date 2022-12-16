package br.com.gabreuw.finances.announcement_search_engine.domain.entities.helper;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import br.com.gabreuw.finances.shared.helper.EnumHelper;
import br.com.gabreuw.finances.shared.helper.InternetHelper;
import br.com.gabreuw.finances.shared.helper.LocalDateHelper;
import br.com.gabreuw.finances.shared.helper.NumberHelper;
import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.HOURS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AnnouncementHelper {

    private static final List<String> ASSETS_CODE = List.of("BBAS3", "PETR4", "VALE3");

    private static final Faker FAKER = Faker.instance();

    public static Announcement createFakeAnnouncement(String assetCode) {
        return Announcement.builder()
                .assetCode(assetCode)
                .title(FAKER.lorem().sentence(5))
                .releaseDate(LocalDateHelper.randomPastDate(1, HOURS))
                .notificationStatus(EnumHelper.randomValue(NotificationStatus.class))
                .downloadUrl(InternetHelper.randomUrl())
                .build();
    }

    public static Announcement createFakeAnnouncement() {
        var randomAssetCode = ASSETS_CODE.get(NumberHelper.randomIntegerBetween(0, ASSETS_CODE.size()));

        return createFakeAnnouncement(randomAssetCode);
    }

    public static List<Announcement> createFakeAnnouncements(int maxAnnouncements) {
        var announcements = ASSETS_CODE.stream()
                .map(AnnouncementHelper::createFakeAnnouncement)
                .toList();

        return Stream.generate(() -> announcements)
                .limit(Math.floorDiv(maxAnnouncements, ASSETS_CODE.size()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
