package com.acme.team7.ACMEDeliveryService.service;

import com.acme.team7.ACMEDeliveryService.base.BaseComponent;
import com.acme.team7.ACMEDeliveryService.domain.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T> {
   public abstract JpaRepository<T, Long> getRepository();

   @Override
   public List<T> createAll(final T... items) {
      return createAll(Arrays.asList(items));
   }

   @Override
   public List<T> createAll(final List<T> items) {
      log.trace("Creating all items in the list.");
      return getRepository().saveAll(items);
   }

   @Override
   public T create(final T item){
      log.trace("Creating item {}.",item);
      return getRepository().save(item);
   }

   @Override
   public void update(final T item){
      log.trace("Updating item {}.",item);
      getRepository().save(item);
   }

   @Override
   public void delete(final T item){
      log.trace("Deleting item {}.",item);
      getRepository().delete(item);
   }

   @Override
   public void deleteById(final Long id){
      log.trace("Deleting item {}",get(id));
      getRepository().deleteById(id);
   }

   @Transactional(readOnly = true)
   @Override
   public T get(final Long id){
      log.trace("Obtaining item with id {}.",id);
      return getRepository().findById(id).orElseThrow(() -> new NoSuchElementException("Item not found"));
   }

   @Transactional(readOnly = true)
   @Override
   public boolean exists(final T item) {
      log.trace("Checking whether {} exists.", item);
      return getRepository().existsById(item.getId());
   }

   @Transactional(readOnly = true)
   @Override
   public List<T> findAll() {
      log.trace("Obtaining all items.");
      return getRepository().findAll();
   }


}
