package app.elmenus.domain.usecases;

import app.elmenus.data.api.callbacks.BaseCallbackWithObject;
import app.elmenus.data.models.Item;
import app.elmenus.data.repository.ItemsRepository;
import app.elmenus.domain.base.UseCase;

public class GetItem extends UseCase<GetItem.RequestValues, GetItem.ResponseValue> {
    private ItemsRepository itemsRepository;

    public GetItem(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemsRepository.getItem(requestValues.itemId, new BaseCallbackWithObject<Item>() {
            @Override
            public void success(Item data) {
                getUseCaseCallback().onSuccess(new ResponseValue(data));
            }

            @Override
            public void error() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private long itemId;

        public RequestValues(long itemId) {
            this.itemId = itemId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private Item item;

        public ResponseValue(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }
    }
}
