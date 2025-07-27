package net.Myapp.journalApp.cache;

import net.Myapp.journalApp.Entity.ConfigJournalAppEntity;
import net.Myapp.journalApp.repository.ConfigJounralAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
public enum keys{
    WEATHER_API
}

    @Autowired
    private ConfigJounralAppRepository configJounralAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJounralAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }

    }



}
