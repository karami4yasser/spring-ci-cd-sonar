### How to deploy the containers ?

1. Make sure you have the `docker.sock` in `/var/run/docker.sock`
2. Run : `docker-compose up -d`
3. when jenkins starts ,Make sure to install the suggested plugins (we will need Git and maven plugins)
4. In jenkins dashboard install `Docker` Plugin
5. Make sure you have the following tools configuration `http://localhost:8080/manage/configureTools/` :

![Screenshot 2024-02-18 at 03.19.26.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F2z%2Fhmx7jq5s29qbk_gs7khry58h0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_sGpffQ%2FScreenshot%202024-02-18%20at%2003.19.26.png)

![Screenshot 2024-02-18 at 03.20.10.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F2z%2Fhmx7jq5s29qbk_gs7khry58h0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_JYPPTG%2FScreenshot%202024-02-18%20at%2003.20.10.png)

6. Create a new jenkins Pipeline , and Make sure to have the following Git configuration
![Screenshot 2024-02-18 at 03.21.43.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F2z%2Fhmx7jq5s29qbk_gs7khry58h0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_heFMRU%2FScreenshot%202024-02-18%20at%2003.21.43.png)