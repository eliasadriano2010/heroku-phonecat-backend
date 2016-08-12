package controllers;

import java.io.IOException;

import util.AjaxReturn;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;

@Resource
public class MediaController {

	private Result result;
	private Validator validator;

	public MediaController(Result result, Validator validator) {
		super();
		this.result = result;
		this.validator = validator;
	}

	// GET /produtos => recupera a lista de todos os produtos. Método lista.
	// @Get("/produtos")
	public void list() {
		System.out.println("ProdutosController --> list()");
		//result.use(Results.json()).from(genericDaoObjectify.getEntities()).recursive().serialize();
	}

	
	
	@Post("/media/image")
	public void uploadPhoto(UploadedFile image) throws IOException {
		
		
		System.out.println("image:" + image.getFile());
		
		AjaxReturn ajaxReturn = new AjaxReturn();
		try{
//			product = genericDaoObjectify.getEntityById(product.getId());
//			product.setImageType(image.getContentType());
//			product.setImageName(image.getFileName());
//			product.setImage(ByteStreams.toByteArray(image.getFile()));
//			genericDaoObjectify.insertEntity(product);
			ajaxReturn.setSucess(true);
		}catch (Exception e) {
			ajaxReturn.setSucess(false);
			//ajaxReturn.addErrors(genericDaoObjectify.getErros());
		}
		result.use(Results.json()).from(ajaxReturn).serialize();
	}

	/*
	 * Métodos temporários a seguir
	 */

	
	@Post
	@Path(value = "/imagem")
	public void salvaImagem(UploadedFile file) throws IOException {
	    System.out.println("SALVAR IMAGEM");
	}
	
	
	
	
//	@Path("/product/{id}/img")  
//    public Download imagem(Long id) {  
//    	System.out.println("ProdutosController --> Download imagem(Long id)"); 
//    	Product product = genericDaoObjectify.getEntityById(id);  
//        return new ByteArrayDownload(product.getImage(), product.getImageType(), product.getImageName());  
//    }  
    
//    @Post
//    public void pegaImagem(UploadedFile myFile) {
//    	System.out.println("ProdutosController --> pegaImagem(UploadedFile myFile)"); 
//    	File seuFile = new File("/path/to/file/repository", myFile.getFileName());
//        System.out.println("file:"+seuFile.getAbsolutePath());
//    }
//    @Post
//    public void pegaImagem2(UploadedFile myFile2) {
//    	System.out.println("ProdutosController --> pegaImagem2(UploadedFile myFile)"); 
//    	File seuFile = new File("/path/to/file/repository", myFile2.getFileName());
//        System.out.println("file:"+seuFile.getAbsolutePath());
//    }

}
