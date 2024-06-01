package org.licenta2024JPA.Repositories;

import org.licenta2024JPA.Entities.Wishlist.Wishlist;
import org.licenta2024JPA.Entities.Wishlist.WishlistId;
import org.licenta2024JPA.Metamodels.AbstractRepository;

public class WishlistRepository extends AbstractRepository<Wishlist> {
    @Override
    protected Class<Wishlist> getEntityClass() {
        return Wishlist.class;
    }

    public Wishlist findById(WishlistId id) {
        return getEm().find(Wishlist.class, id);
    }

    public void addWishlist(Wishlist wishlist) {
        try {
            beginTransaction();
            create(wishlist);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void updateWishlist(Wishlist wishlist) {
        try {
            beginTransaction();
            update(wishlist);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }

    public void deleteWishlist(Wishlist wishlist) {
        try {
            beginTransaction();
            delete(wishlist);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }
}