from django.urls import path

from post import views

urlpatterns = [
    path("index/", views.index_view, name="post_index")
]