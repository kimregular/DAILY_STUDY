from django.http import HttpResponse

from post.models import Post


# Create your views here.

def posts_view(request):
    posts = Post.objects.all()
    result = ", ".join([p.title for p in posts])
    return HttpResponse(result)