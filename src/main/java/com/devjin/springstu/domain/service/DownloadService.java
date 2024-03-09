package com.devjin.springstu.domain.service;
import com.devjin.springstu.domain.dto.request.ReqPostDownload;
import com.devjin.springstu.domain.dto.response.ResDownload;
import com.devjin.springstu.domain.dto.response.ResUser;
import com.devjin.springstu.domain.entity.User;
import com.devjin.springstu.domain.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final UserRepository userRepository;
    public DownloadService(UserRepository _userRepository)
    {
        this.userRepository = _userRepository;
    }
    @Transactional(readOnly = true)
    private User getUser(String _id)
    {
        return userRepository.findById(_id).orElse(null);
    }

    private HttpServletResponse downloadFile(HttpServletResponse response, String _id) throws IOException
    {
        User user=getUser(_id);

        if(user==null)return response;

        String version = user.getVersion();
        String path = "/Users/kimjinyoung/TestFolder/"+version+"/TIMSIM.exe";
        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode("TIMSIM.exe", "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
        return response;
    }

    public ResDownload download(HttpServletResponse response, ReqPostDownload reqPostDownload) throws IOException
    {
        ResDownload resDownload = new ResDownload(downloadFile(response,reqPostDownload.getID()));
        return  resDownload;
    }
}
