package app.elmenus.domain.usecases;

import java.util.List;

import app.elmenus.data.api.callbacks.BaseCallbackWithList;
import app.elmenus.data.models.Item;
import app.elmenus.data.repository.ItemsRepository;
import app.elmenus.domain.base.UseCase;

public class GetItems extends UseCase<GetItems.RequestValues, GetItems.ResponseValue> {
    private ItemsRepository itemsRepository;

    public GetItems(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        itemsRepository.getItems(requestValues.page, new BaseCallbackWithList<Item>() {
            @Override
            public void success(List<Item> ListOfData) {
                getUseCaseCallback().onSuccess(new ResponseValue(ListOfData));
            }

            @Override
            public void error() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int page;

        public RequestValues(int page) {
            this.page = page;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<Item> items;

        public ResponseValue(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }
    }
}
